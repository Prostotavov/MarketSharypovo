package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "cart_items",
    primaryKeys = ["serviceId"],
    foreignKeys = [
        ForeignKey(
            entity = Service::class,
            parentColumns = ["id"],
            childColumns = ["serviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CartItem(
    val serviceId: Long,
    val hours: Int,
    val addedAt: Long = System.currentTimeMillis()
) 