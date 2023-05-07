package com.example.githubusers.data.network

import com.example.githubusers.data.models.UserDetailData
import com.example.githubusers.data.models.UsersData
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users")
    suspend fun getAllUsers(): UsersData

    @GET("users/{login}")
    suspend fun getUserDetailByLogin(@Path("login") login: String): UserDetailData
}