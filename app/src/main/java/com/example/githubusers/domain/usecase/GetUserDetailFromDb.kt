package com.example.githubusers.domain.usecase

import com.example.githubusers.domain.repository.UserRepository

class GetUserDetailFromDb(private val userRepository: UserRepository) {
    suspend fun getByLogin(login: String) = userRepository.getUserDetailByLogin(login = login)
}