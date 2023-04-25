package com.example.githubusers.domain.repository

import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail

interface UserRepository {
    suspend fun getAllUsers(): List<User>?

    suspend fun getUserDetailByLogin(login: String): UserDetail?

    suspend fun saveUsers(users: List<User>): Boolean

    suspend fun deleteAllUsers(): Boolean

    suspend fun saveUserDetailToDb(userDetail: UserDetail): Boolean
}