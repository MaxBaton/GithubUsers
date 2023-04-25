package com.example.githubusers.data.database.user

import androidx.room.*
import com.example.githubusers.data.models.UserData

@Dao
interface UserDao {
    @Query("select * from userData")
    fun getUsers(): List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(userData: UserData)

    @Delete
    fun deleteUser(userData: UserData)

    @Query("delete from userData")
    fun clear()
}