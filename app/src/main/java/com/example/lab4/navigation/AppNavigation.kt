package com.example.lab4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab4.data.model.Service
import com.example.lab4.ui.screens.CartScreen
import com.example.lab4.ui.screens.LoginScreen
import com.example.lab4.ui.screens.ProfileScreen
import com.example.lab4.ui.screens.RegisterScreen
import com.example.lab4.ui.screens.ServiceDetailScreen
import com.example.lab4.ui.screens.ServiceListScreen
import com.example.lab4.ui.viewmodel.CartViewModel
import com.example.lab4.ui.viewmodel.ServiceViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ServiceList : Screen("serviceList")
    object ServiceDetail : Screen("serviceDetail/{serviceId}") {
        fun createRoute(serviceId: Long) = "serviceDetail/$serviceId"
    }
    object Cart : Screen("cart")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    serviceViewModel: ServiceViewModel,
    cartViewModel: CartViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Screen.ServiceList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(Screen.ServiceList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBackToLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ServiceList.route) {
            ServiceListScreen(
                viewModel = serviceViewModel,
                onServiceClick = { service ->
                    navController.navigate(Screen.ServiceDetail.createRoute(service.id))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.ServiceDetail.route) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")?.toLongOrNull() ?: return@composable
            val services by serviceViewModel.services.collectAsState()
            val service = services.find { it.id == serviceId } ?: return@composable
            
            ServiceDetailScreen(
                service = service,
                cartViewModel = cartViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                cartViewModel = cartViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.ServiceList.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
} 