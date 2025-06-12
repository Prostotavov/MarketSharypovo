package com.example.lab4.di

import android.content.Context
import androidx.room.Room
import com.example.lab4.data.AppDatabase
import com.example.lab4.data.dao.CartDao
import com.example.lab4.data.dao.ServiceDao
import com.example.lab4.data.repository.CartRepository
import com.example.lab4.data.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideServiceDao(database: AppDatabase): ServiceDao {
        return database.serviceDao()
    }
    
    @Provides
    @Singleton
    fun provideCartDao(database: AppDatabase): CartDao {
        return database.cartDao()
    }
    
    @Provides
    @Singleton
    fun provideServiceRepository(serviceDao: ServiceDao): ServiceRepository {
        return ServiceRepository(serviceDao)
    }
    
    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepository(cartDao)
    }
} 