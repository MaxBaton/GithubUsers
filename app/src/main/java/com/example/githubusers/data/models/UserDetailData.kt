package com.example.githubusers.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailData(
    val avatar_url: String?,
    val email: String?,
    @PrimaryKey
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val organizations_url: String?,
    val twitter_username: String?,
    val url: String?
)
