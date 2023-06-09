package com.example.githubusers.data.repository

import com.example.githubusers.data.mappers.mapToListUser
import com.example.githubusers.data.mappers.mapToListUserData
import com.example.githubusers.data.mappers.mapToUserDetail
import com.example.githubusers.data.mappers.mapToUserDetailData
import com.example.githubusers.data.repository.storage.UserStorage
import com.example.githubusers.data.repository.storage.operations.UserAdditionalOperations
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail
import com.example.githubusers.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorage: UserStorage,
    private val userAdditionalOperations: UserAdditionalOperations
): UserRepository {
    override suspend fun getAllUsers(): List<User>? {
        val usersData = userStorage.getAllUsers()
        return usersData?.mapToListUser()
    }

    override suspend fun getUserDetailByLogin(login: String): UserDetail? {
        val userDetailData = userStorage.getUserDetailByLogin(login = login)
        return userDetailData?.mapToUserDetail()
    }

    override suspend fun saveUsers(users: List<User>): Boolean {
        val usersData = users.mapToListUserData()
        return userAdditionalOperations.saveUsers(usersData = usersData)
    }

    override suspend fun deleteAllUsers() = userAdditionalOperations.deleteAllUsers()
    override suspend fun saveUserDetailToDb(userDetail: UserDetail): Boolean {
        val userDetailData = userDetail.mapToUserDetailData()
        return userAdditionalOperations.saveUserDetailToDb(userDetailData = userDetailData)
    }
}