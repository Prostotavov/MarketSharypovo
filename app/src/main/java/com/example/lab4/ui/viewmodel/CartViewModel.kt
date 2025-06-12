package com.example.lab4.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab4.data.model.CartItem
import com.example.lab4.data.model.Service
import com.example.lab4.data.repository.CartRepository
import com.example.lab4.data.repository.ServiceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository,
    private val serviceRepository: ServiceRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    val cartServices: StateFlow<List<Pair<Service, Int>>> = combine(
        cartItems,
        serviceRepository.getAllServices()
    ) { items, services ->
        items.mapNotNull { cartItem ->
            services.find { it.id == cartItem.serviceId }?.let { service ->
                Pair(service, cartItem.hours)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val totalPrice: StateFlow<Double> = cartServices.map { services ->
        services.sumOf { (service, hours) -> service.pricePerHour * hours }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    init {
        viewModelScope.launch {
            cartRepository.getAllCartItems().collect { items ->
                _cartItems.value = items
            }
        }
    }

    fun addToCart(service: Service, hours: Int) {
        viewModelScope.launch {
            val existingItem = cartRepository.getCartItemByServiceId(service.id)
            if (existingItem != null) {
                cartRepository.updateCartItem(existingItem.copy(hours = existingItem.hours + hours))
            } else {
                cartRepository.addToCart(CartItem(service.id, hours))
            }
        }
    }

    fun updateCartItemHours(serviceId: Long, hours: Int) {
        viewModelScope.launch {
            cartRepository.getCartItemByServiceId(serviceId)?.let { item ->
                cartRepository.updateCartItem(item.copy(hours = hours))
            }
        }
    }

    fun removeFromCart(serviceId: Long) {
        viewModelScope.launch {
            cartRepository.getCartItemByServiceId(serviceId)?.let { item ->
                cartRepository.removeFromCart(item)
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }

    class Factory(
        private val cartRepository: CartRepository,
        private val serviceRepository: ServiceRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartViewModel(cartRepository, serviceRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
} 