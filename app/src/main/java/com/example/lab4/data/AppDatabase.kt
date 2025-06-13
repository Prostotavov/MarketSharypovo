package com.example.lab4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab4.data.dao.CartDao
import com.example.lab4.data.dao.CommentDao
import com.example.lab4.data.dao.ServiceDao
import com.example.lab4.data.model.CartItem
import com.example.lab4.data.model.Comment
import com.example.lab4.data.model.Service
import com.example.lab4.data.model.ServiceCategory

@Database(
    entities = [Service::class, CartItem::class, Comment::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
    abstract fun cartDao(): CartDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 