package com.example.githubusers.app.presentation.di.data

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserDbStorage

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserNetworkStorage

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserDbRepository

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserNetworkRepository