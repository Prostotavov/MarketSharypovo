package com.example.lab4.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab4.ui.screens.HomeScreen
import com.example.lab4.ui.screens.ProfileScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Services : Screen("services")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToServices = {
                    navController.navigate(Screen.Services.route)
                }
            )
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        
        composable(Screen.Services.route) {
            // TODO: Добавить экран услуг
        }
    }
} 