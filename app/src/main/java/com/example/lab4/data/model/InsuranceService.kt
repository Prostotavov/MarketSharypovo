package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "insurance_services")
data class InsuranceService(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val providerId: Long,
    val providerName: String,
    val rating: Float = 0f,
    val reviewsCount: Int = 0,
    val city: String = "Шарыпово"
) 