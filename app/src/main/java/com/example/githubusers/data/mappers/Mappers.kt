package com.example.githubusers.data.mappers

import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail

fun UserData.mapToUser(): User {
    return User(
        avatar_url = this.avatar_url,
        id = this.id,
        login  = this.login,
        organizations_url = this.organizations_url,
        repos_url = this.repos_url,
        site_admin = this.site_admin,
        url = this.url
    )
}

fun User.mapToUserData(): UserData {
    return UserData(
        avatar_url = this.avatar_url,
        id = this.id,
        login  = this.login,
        organizations_url = this.organizations_url,
        repos_url = this.repos_url,
        site_admin = this.site_admin,
        url = this.url
    )
}

fun List<UserData>.mapToListUser(): List<User> {
    val list = mutableListOf<User>()
    this.forEach {
        list.add(it.mapToUser())
    }

    return list
}

fun List<User>.mapToListUserData(): List<UserData> {
    val list = mutableListOf<UserData>()
    this.forEach {
        list.add(it.mapToUserData())
    }

    return list
}

fun UserDetailData.mapToUserDetail(): UserDetail {
    return UserDetail(
        avatar_url = this.avatar_url,
        email = this.email,
        id = this.id,
        location = this.location,
        login = this.login,
        name = this.name,
        organizations_url = this.organizations_url,
        twitter_username = this.twitter_username,
        url = this.url
    )
}

fun UserDetail.mapToUserDetailData(): UserDetailData {
    return UserDetailData(
        avatar_url = this.avatar_url,
        email = this.email,
        id = this.id,
        location = this.location,
        login = this.login,
        name = this.name,
        organizations_url = this.organizations_url,
        twitter_username = this.twitter_username,
        url = this.url
    )
}