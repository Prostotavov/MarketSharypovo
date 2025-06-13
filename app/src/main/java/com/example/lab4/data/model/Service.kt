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

enum class ServiceCategory(val displayName: String) {
    TUTORING("Репетиторство"),
    REPAIR("Ремонт"),
    CLEANING("Уборка"),
    BEAUTY("Красота"),
    FITNESS("Фитнес"),
    IT("IT-услуги"),
    DESIGN("Дизайн"),
    TRANSPORT("Транспорт"),
    EDUCATION("Образование"),
    HEALTH("Здоровье"),
    LEGAL("Юридические услуги"),
    CONSTRUCTION("Строительство"),
    GARDENING("Садоводство"),
    PETS("Уход за животными"),
    PHOTOGRAPHY("Фотография"),
    COOKING("Кулинария"),
    LANGUAGE("Языки"),
    MUSIC("Музыка"),
    DANCE("Танцы"),
    YOGA("Йога")
} 