package com.example.githubusers.data.repository.storage

import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData

interface UserStorage {
    suspend fun getAllUsers(): List<UserData>?

    suspend fun getUserDetailByLogin(login: String): UserDetailData?

    suspend fun saveUsers(usersData: List<UserData>): Boolean

    suspend fun deleteAllUsers(): Boolean

    suspend fun saveUserDetailToDb(userDetailData: UserDetailData): Boolean
}