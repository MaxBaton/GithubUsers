package com.example.githubusers.data.repository.storage.operations

import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData

interface UserAdditionalOperations {
    suspend fun saveUsers(usersData: List<UserData>): Boolean

    suspend fun deleteAllUsers(): Boolean

    suspend fun saveUserDetailToDb(userDetailData: UserDetailData): Boolean
}