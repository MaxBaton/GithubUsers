package com.example.githubusers.domain.usecase

import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.repository.UserRepository

class SaveUsers(private val userRepository: UserRepository) {
    suspend fun saveUsers(users: List<User>) = userRepository.saveUsers(users = users)
}