package com.example.lab4.data.repository

import com.example.lab4.data.dao.UserDao
import com.example.lab4.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUsers(): Flow<List<User>> =
        userDao.getAllUsers()
    
    suspend fun getUserById(id: Long): User? =
        userDao.getUserById(id)
    
    suspend fun getUserByEmail(email: String): User? =
        userDao.getUserByEmail(email)
    
    suspend fun addUser(user: User): Long =
        userDao.insertUser(user)
    
    suspend fun updateUser(user: User) =
        userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) =
        userDao.deleteUser(user)
    
    fun getAllProviders(): Flow<List<User>> =
        userDao.getAllProviders()
} 