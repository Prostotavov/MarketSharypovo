package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val serviceId: Long,
    val userId: Long,
    val userName: String,
    val rating: Float,
    val comment: String,
    val date: Long = System.currentTimeMillis()
) 