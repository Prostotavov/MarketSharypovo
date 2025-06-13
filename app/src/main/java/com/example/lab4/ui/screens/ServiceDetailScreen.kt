package com.example.lab4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab4.data.model.Service
import com.example.lab4.ui.viewmodel.CartViewModel
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailScreen(
    service: Service,
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit,
    onCommentsClick: () -> Unit
) {
    var showRemoveDialog by remember { mutableStateOf(false) }
    var showAddToCartDialog by remember { mutableStateOf(false) }
    var hours by remember { mutableStateOf(1) }
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val isInCart = cartItems.any { cartItem -> cartItem.serviceId == service.id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(service.name) },
                navigationIcon = {
                    TextButton(onClick = onBackClick) {
                        Text("Назад")
                    }
                },
                actions = {
                    TextButton(onClick = onCommentsClick) {
                        Text("Комментарии")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Цена: ${NumberFormat.getCurrencyInstance(Locale("ru", "RU")).format(service.pricePerHour)}/час",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Рейтинг: ${service.rating}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (isInCart) {
                        showRemoveDialog = true
                    } else {
                        showAddToCartDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isInCart) "Удалить из корзины" else "Добавить в корзину")
            }
        }

        if (showRemoveDialog) {
            AlertDialog(
                onDismissRequest = { showRemoveDialog = false },
                title = { Text("Удалить из корзины") },
                text = { Text("Вы уверены, что хотите удалить эту услугу из корзины?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            cartViewModel.removeFromCart(service.id)
                            showRemoveDialog = false
                        }
                    ) {
                        Text("Удалить")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showRemoveDialog = false }) {
                        Text("Отмена")
                    }
                }
            )
        }

        if (showAddToCartDialog) {
            AlertDialog(
                onDismissRequest = { showAddToCartDialog = false },
                title = { Text("Добавить в корзину") },
                text = {
                    Column {
                        Text("Выберите количество часов:")
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = { if (hours > 1) hours-- }
                            ) {
                                Text("-")
                            }
                            Text(
                                text = hours.toString(),
                                style = MaterialTheme.typography.titleLarge
                            )
                            TextButton(
                                onClick = { hours++ }
                            ) {
                                Text("+")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Итого: ${NumberFormat.getCurrencyInstance(Locale("ru", "RU")).format(service.pricePerHour * hours)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            cartViewModel.addToCart(service, hours)
                            showAddToCartDialog = false
                        }
                    ) {
                        Text("Добавить")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddToCartDialog = false }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
} 