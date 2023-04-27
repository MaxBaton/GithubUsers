package com.example.githubusers.data.repository.storage.operations

import com.example.githubusers.data.database.user.UserDao
import com.example.githubusers.data.database.user.UserDetailDao
import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData

class UsersAdditionalDbOperationsImpl(
    private val userDao: UserDao,
    private val userDetailDao: UserDetailDao
): UserAdditionalOperations {
    override suspend fun saveUsers(usersData: List<UserData>): Boolean {
        return try {
            usersData.forEach {
                userDao.add(userData = it)
            }
            true
        }catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteAllUsers(): Boolean {
        return try {
            userDao.clear()
            true
        }catch (e: Exception) {
            false
        }
    }

    override suspend fun saveUserDetailToDb(userDetailData: UserDetailData): Boolean {
        return try {
            userDetailDao.add(userDetailData = userDetailData)
            true
        }catch (e: Exception) {
            false
        }
    }
}