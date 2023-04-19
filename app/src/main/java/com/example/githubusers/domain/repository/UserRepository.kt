package com.example.githubusers.domain.repository

import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail

interface UserRepository {
    suspend fun getAllUsers(): List<User>?

    suspend fun getUserDetailByLogin(login: String): UserDetail?
}