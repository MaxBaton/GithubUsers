package com.example.githubusers.data.mappers

import com.example.githubusers.data.models.UserData
import com.example.githubusers.data.models.UserDetailData
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail

fun UserData.mapToUser(): User {
    return User(
        avatar_url = this.avatar_url,
        events_url = this.events_url,
        followers_url = this.followers_url,
        following_url = this.following_url,
        gists_url = this.gists_url,
        gravatar_id = this.gravatar_id,
        html_url = this.html_url,
        id = this.id,
        login  = this.login,
        node_id = this.node_id,
        organizations_url = this.organizations_url,
        received_events_url = this.received_events_url,
        repos_url = this.repos_url,
        site_admin = this.site_admin,
        starred_url = this.starred_url,
        subscriptions_url = this.subscriptions_url,
        type = this.type,
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

fun UserDetailData.mapToUserDetail(): UserDetail {
    return UserDetail(
        avatar_url = this.avatar_url,
        bio = this.bio,
        blog = this.blog,
        company = this.company,
        created_at = this.created_at,
        email = this.email,
        events_url = this.events_url,
        followers = this.followers,
        followers_url = this.followers_url,
        following = this.following,
        following_url = this.following_url,
        gists_url = this.gists_url,
        gravatar_id = this.gravatar_id,
        hireable = this.hireable,
        html_url = this.html_url,
        id = this.id,
        location = this.location,
        login = this.login,
        name = this.name,
        node_id = this.node_id,
        organizations_url = this.organizations_url,
        public_gists = this.public_gists,
        public_repos = this.public_repos,
        received_events_url = this.received_events_url,
        repos_url = this.repos_url,
        site_admin = this.site_admin,
        starred_url = this.starred_url,
        subscriptions_url = this.subscriptions_url,
        twitter_username = this.twitter_username,
        type = this.type,
        updated_at = this.updated_at,
        url = this.url
    )
}