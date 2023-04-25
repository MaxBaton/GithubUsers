package com.example.githubusers.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    val avatar_url: String,
    @PrimaryKey
    val id: Int,
    val login: String,
    val organizations_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val url: String
)
