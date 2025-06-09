package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val phone: String,
    val isProvider: Boolean = false,
    val rating: Float = 0f,
    val reviewsCount: Int = 0,
    val city: String = "Шарыпово"
) 