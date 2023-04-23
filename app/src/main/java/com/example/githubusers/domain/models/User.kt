package com.example.githubusers.domain.models

data class User(
    val avatar_url: String,
    val id: Int,
    val login: String,
    val organizations_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val url: String
)