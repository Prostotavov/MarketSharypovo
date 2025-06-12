package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lab4.data.AppDatabase
import com.example.lab4.data.repository.CartRepository
import com.example.lab4.data.repository.ServiceRepository
import com.example.lab4.navigation.AppNavigation
import com.example.lab4.ui.theme.Lab4Theme
import com.example.lab4.ui.viewmodel.CartViewModel
import com.example.lab4.ui.viewmodel.ServiceViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val serviceRepository = ServiceRepository(database.serviceDao())
        val cartRepository = CartRepository(database.cartDao())

        // Добавляем тестовые данные при первом запуске
        CoroutineScope(Dispatchers.IO).launch {
            serviceRepository.insertInitialData()
        }

        setContent {
            Lab4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val serviceViewModel: ServiceViewModel = viewModel(
                        factory = ServiceViewModel.Factory(serviceRepository)
                    )
                    val cartViewModel: CartViewModel = viewModel(
                        factory = CartViewModel.Factory(cartRepository, serviceRepository)
                    )

                    AppNavigation(
                        navController = navController,
                        serviceViewModel = serviceViewModel,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }
    }
}