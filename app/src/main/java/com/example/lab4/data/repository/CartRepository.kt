package com.example.lab4.data.repository

import com.example.lab4.data.dao.CartDao
import com.example.lab4.data.model.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    fun getAllCartItems(): Flow<List<CartItem>> = cartDao.getAllCartItems()

    suspend fun addToCart(cartItem: CartItem) = cartDao.insertCartItem(cartItem)

    suspend fun updateCartItem(cartItem: CartItem) = cartDao.updateCartItem(cartItem)

    suspend fun removeFromCart(cartItem: CartItem) = cartDao.deleteCartItem(cartItem)

    suspend fun clearCart() = cartDao.clearCart()

    suspend fun getCartItemByServiceId(serviceId: Long): CartItem? =
        cartDao.getCartItemByServiceId(serviceId)
} 