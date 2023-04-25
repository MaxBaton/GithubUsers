package com.example.githubusers.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubusers.data.models.UserDetailData

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(userDetailData: UserDetailData)

    @Query("select * from userDetailData where login = :login")
    fun getDetailByLogin(login: String): UserDetailData
}