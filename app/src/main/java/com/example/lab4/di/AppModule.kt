package com.example.lab4.di

import android.content.Context
import com.example.lab4.data.AppDatabase
import com.example.lab4.data.dao.InsuranceServiceDao
import com.example.lab4.data.dao.ReviewDao
import com.example.lab4.data.dao.UserDao
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
    ): AppDatabase = AppDatabase.getDatabase(context)
    
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
    
    @Provides
    fun provideInsuranceServiceDao(database: AppDatabase): InsuranceServiceDao = 
        database.insuranceServiceDao()
    
    @Provides
    fun provideReviewDao(database: AppDatabase): ReviewDao = database.reviewDao()
} 