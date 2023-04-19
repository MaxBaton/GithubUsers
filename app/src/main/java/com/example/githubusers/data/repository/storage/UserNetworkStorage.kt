package com.example.githubusers.data.repository.storage

import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData
import com.example.githubusers.data.network.UserApi

class UserNetworkStorage(private val userApi: UserApi): UserStorage {
    override suspend fun getAllUsers(): List<UserData>? {
        return try {
            val userData = userApi.getAllUsers()
            userData
        }catch (e: Exception) {
            null
        }
    }

    override suspend fun getUserDetailByLogin(login: String): UserDetailData? {
        return try {
            val userDetail = userApi.getUserDetailByLogin(login = login)
            userDetail
        }catch (e: Exception) {
            null
        }
    }
}