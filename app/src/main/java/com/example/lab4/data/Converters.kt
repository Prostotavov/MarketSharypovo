package com.example.lab4.data

import androidx.room.TypeConverter
import com.example.lab4.data.model.ServiceCategory

class Converters {
    @TypeConverter
    fun fromServiceCategory(category: ServiceCategory): String {
        return category.name
    }

    @TypeConverter
    fun toServiceCategory(value: String): ServiceCategory {
        return ServiceCategory.valueOf(value)
    }
} 