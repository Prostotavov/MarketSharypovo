package com.example.lab4.data.dao

import androidx.room.*
import com.example.lab4.data.model.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews WHERE serviceId = :serviceId")
    fun getReviewsForService(serviceId: Long): Flow<List<Review>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: Review): Long
    
    @Update
    suspend fun updateReview(review: Review)
    
    @Delete
    suspend fun deleteReview(review: Review)
    
    @Query("SELECT AVG(rating) FROM reviews WHERE serviceId = :serviceId")
    suspend fun getAverageRatingForService(serviceId: Long): Float?
} 