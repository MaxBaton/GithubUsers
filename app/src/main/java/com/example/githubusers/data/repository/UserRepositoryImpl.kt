package com.example.githubusers.data.repository

import com.example.githubusers.data.mappers.mapToListUser
import com.example.githubusers.data.mappers.mapToUserDetail
import com.example.githubusers.data.repository.storage.UserStorage
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail
import com.example.githubusers.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage): UserRepository {
    override suspend fun getAllUsers(): List<User>? {
        val usersData = userStorage.getAllUsers()
        return usersData?.mapToListUser()
    }

    override suspend fun getUserDetailByLogin(login: String): UserDetail? {
        val userDetailData = userStorage.getUserDetailByLogin(login = login)
        return userDetailData?.mapToUserDetail()
    }
}