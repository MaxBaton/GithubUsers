package com.example.githubusers.data.models

data class UserData(
    val avatar_url: String,
    val id: Int,
    val login: String,
    val organizations_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val url: String
)
