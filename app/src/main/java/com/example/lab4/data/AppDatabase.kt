package com.example.lab4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab4.data.dao.InsuranceServiceDao
import com.example.lab4.data.dao.ReviewDao
import com.example.lab4.data.dao.UserDao
import com.example.lab4.data.model.InsuranceService
import com.example.lab4.data.model.Review
import com.example.lab4.data.model.User

@Database(
    entities = [User::class, InsuranceService::class, Review::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun insuranceServiceDao(): InsuranceServiceDao
    abstract fun reviewDao(): ReviewDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "insurance_marketplace_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 