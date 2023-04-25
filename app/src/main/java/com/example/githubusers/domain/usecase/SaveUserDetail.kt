package com.example.githubusers.domain.usecase

import com.example.githubusers.domain.models.UserDetail
import com.example.githubusers.domain.repository.UserRepository

class SaveUserDetail(private val userRepository: UserRepository) {
    suspend fun save(userDetail: UserDetail) = userRepository.saveUserDetailToDb(userDetail = userDetail)
}