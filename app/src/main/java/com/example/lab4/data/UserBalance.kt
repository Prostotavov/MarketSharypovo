package com.example.lab4.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserBalance {
    private val _balance = MutableStateFlow(10000.0)
    val balance: StateFlow<Double> = _balance.asStateFlow()

    fun subtractAmount(amount: Double) {
        _balance.value -= amount
    }

    fun getCurrentBalance(): Double = _balance.value
} 