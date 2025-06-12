package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val category: ServiceCategory,
    val pricePerHour: Double,
    val rating: Float,
    val imageUrl: String,
    val providerName: String,
    val providerPhone: String,
    val isAvailable: Boolean = true
)

enum class ServiceCategory {
    TUTORING,
    REPAIR,
    CLEANING,
    BEAUTY,
    FITNESS,
    IT,
    DESIGN,
    TRANSPORT,
    EDUCATION,
    HEALTH,
    LEGAL,
    CONSTRUCTION,
    GARDENING,
    PETS,
    PHOTOGRAPHY,
    COOKING,
    LANGUAGE,
    MUSIC,
    DANCE,
    YOGA
} 