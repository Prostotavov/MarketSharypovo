package com.example.lab4.data.dao

import androidx.room.*
import com.example.lab4.data.model.InsuranceService
import kotlinx.coroutines.flow.Flow

@Dao
interface InsuranceServiceDao {
    @Query("SELECT * FROM insurance_services")
    fun getAllServices(): Flow<List<InsuranceService>>
    
    @Query("SELECT * FROM insurance_services WHERE category = :category")
    fun getServicesByCategory(category: String): Flow<List<InsuranceService>>
    
    @Query("SELECT * FROM insurance_services WHERE providerId = :providerId")
    fun getServicesByProvider(providerId: Long): Flow<List<InsuranceService>>
    
    @Query("SELECT * FROM insurance_services WHERE id = :id")
    suspend fun getServiceById(id: Long): InsuranceService?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: InsuranceService): Long
    
    @Update
    suspend fun updateService(service: InsuranceService)
    
    @Delete
    suspend fun deleteService(service: InsuranceService)
    
    @Query("SELECT * FROM insurance_services WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchServices(query: String): Flow<List<InsuranceService>>
} 