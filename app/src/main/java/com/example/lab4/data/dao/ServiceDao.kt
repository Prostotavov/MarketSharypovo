package com.example.lab4.data.dao

import androidx.room.*
import com.example.lab4.data.model.Service
import com.example.lab4.data.model.ServiceCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Query("SELECT * FROM services")
    fun getAllServices(): Flow<List<Service>>

    @Query("SELECT * FROM services WHERE category = :category")
    fun getServicesByCategory(category: ServiceCategory): Flow<List<Service>>

    @Query("SELECT * FROM services WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchServices(query: String): Flow<List<Service>>

    @Query("SELECT * FROM services ORDER BY pricePerHour ASC")
    fun getServicesByPriceAsc(): Flow<List<Service>>

    @Query("SELECT * FROM services ORDER BY pricePerHour DESC")
    fun getServicesByPriceDesc(): Flow<List<Service>>

    @Query("SELECT * FROM services ORDER BY rating DESC")
    fun getServicesByRating(): Flow<List<Service>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServices(services: List<Service>)

    @Update
    suspend fun updateService(service: Service)

    @Delete
    suspend fun deleteService(service: Service)

    @Query("DELETE FROM services")
    suspend fun deleteAllServices()
} 