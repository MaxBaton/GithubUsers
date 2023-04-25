package com.example.githubusers.domain.usecase

import com.example.githubusers.domain.repository.UserRepository

class DeleteUsersFromDb(private val userRepository: UserRepository) {
    suspend fun delete() = userRepository.deleteAllUsers()
}