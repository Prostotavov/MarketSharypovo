package com.example.lab4.data.repository

import com.example.lab4.data.dao.InsuranceServiceDao
import com.example.lab4.data.dao.ReviewDao
import com.example.lab4.data.model.InsuranceService
import com.example.lab4.data.model.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsuranceRepository @Inject constructor(
    private val insuranceServiceDao: InsuranceServiceDao,
    private val reviewDao: ReviewDao
) {
    fun getAllServices(): Flow<List<InsuranceService>> = 
        insuranceServiceDao.getAllServices()
    
    fun getServicesByCategory(category: String): Flow<List<InsuranceService>> =
        insuranceServiceDao.getServicesByCategory(category)
    
    fun getServicesByProvider(providerId: Long): Flow<List<InsuranceService>> =
        insuranceServiceDao.getServicesByProvider(providerId)
    
    suspend fun getServiceById(id: Long): InsuranceService? =
        insuranceServiceDao.getServiceById(id)
    
    suspend fun addService(service: InsuranceService): Long =
        insuranceServiceDao.insertService(service)
    
    suspend fun updateService(service: InsuranceService) =
        insuranceServiceDao.updateService(service)
    
    suspend fun deleteService(service: InsuranceService) =
        insuranceServiceDao.deleteService(service)
    
    fun searchServices(query: String): Flow<List<InsuranceService>> =
        insuranceServiceDao.searchServices(query)
    
    fun getReviewsForService(serviceId: Long): Flow<List<Review>> =
        reviewDao.getReviewsForService(serviceId)
    
    suspend fun addReview(review: Review): Long =
        reviewDao.insertReview(review)
    
    suspend fun getAverageRatingForService(serviceId: Long): Float? =
        reviewDao.getAverageRatingForService(serviceId)
} 