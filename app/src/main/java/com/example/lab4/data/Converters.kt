package com.example.lab4.data

import androidx.room.TypeConverter
import com.example.lab4.data.model.ServiceCategory
import java.util.Date

class Converters {
    @TypeConverter
    fun fromServiceCategory(category: ServiceCategory): String {
        return category.name
    }

    @TypeConverter
    fun toServiceCategory(value: String): ServiceCategory {
        return ServiceCategory.valueOf(value)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
} 