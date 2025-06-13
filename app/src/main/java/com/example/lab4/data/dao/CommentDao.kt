package com.example.lab4.data.dao

import androidx.room.*
import com.example.lab4.data.model.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments WHERE serviceId = :serviceId ORDER BY date DESC")
    fun getCommentsForService(serviceId: Long): Flow<List<Comment>>

    @Insert
    suspend fun insertComment(comment: Comment)

    @Delete
    suspend fun deleteComment(comment: Comment)

    @Query("SELECT AVG(rating) FROM comments WHERE serviceId = :serviceId")
    suspend fun getAverageRatingForService(serviceId: Long): Float?
} 