package com.example.githubusers.data.repository.storage

import com.example.githubusers.data.database.user.UserDao
import com.example.githubusers.data.database.user.UserDetailDao
import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData
import kotlin.math.log

class UserDbStorage(
    private val userDao: UserDao,
    private val userDetailDao: UserDetailDao
): UserStorage {
    override suspend fun getAllUsers(): List<UserData>? {
        return try {
            userDao.getUsers()
        }catch (e: Exception) {
            null
        }
    }

    override suspend fun getUserDetailByLogin(login: String): UserDetailData? {
        return try {
            userDetailDao.getDetailByLogin(login = login)
        }catch (e: Exception) {
            null
        }
    }
}