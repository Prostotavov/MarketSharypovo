package com.example.lab4.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = Service::class,
            parentColumns = ["id"],
            childColumns = ["serviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("serviceId")]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val serviceId: Long,
    val userName: String,
    val text: String,
    val rating: Float,
    val date: Date
) 