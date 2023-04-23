package com.example.githubusers.domain.models

data class UserDetail(
    val avatar_url: String,
    val email: String?,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String,
    val organizations_url: String,
    val twitter_username: String?,
    val url: String
)