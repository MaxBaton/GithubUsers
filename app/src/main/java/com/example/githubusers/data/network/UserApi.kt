package com.example.githubusers.data.network

import com.example.githubusers.data.models.UserDetailData
import com.example.githubusers.data.models.UsersData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserApi {
    @Headers("Accept: application/vnd.github+json")
    @GET("users")
    suspend fun getAllUsers(@Header("Authorization") token: String): UsersData

    @GET("users/{login}")
    suspend fun getUserDetailByLogin(@Path("login") login: String): UserDetailData
}