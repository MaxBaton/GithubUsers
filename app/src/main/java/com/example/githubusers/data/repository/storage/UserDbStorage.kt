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