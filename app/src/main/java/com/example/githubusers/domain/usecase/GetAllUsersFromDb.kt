package com.example.githubusers.domain.usecase

import com.example.githubusers.domain.repository.UserRepository

class GetAllUsersFromDb(private val userRepository: UserRepository) {
    suspend fun get() = userRepository.getAllUsers()
}