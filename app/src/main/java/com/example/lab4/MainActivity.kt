package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
    private val database by lazy { AppDatabase.getDatabase(applicationContext) }
    private val serviceRepository by lazy { ServiceRepository(database.serviceDao()) }
    private val cartRepository by lazy { CartRepository(database.cartDao()) }

    private val serviceViewModel: ServiceViewModel by viewModels { 
        ServiceViewModel.Factory(application)
    }
    private val cartViewModel: CartViewModel by viewModels { 
        CartViewModel.Factory(cartRepository, serviceRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    AppNavigation(
                        navController = rememberNavController(),
                        serviceViewModel = serviceViewModel,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }
    }
}