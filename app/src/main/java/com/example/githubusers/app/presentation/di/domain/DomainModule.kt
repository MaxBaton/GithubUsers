package com.example.githubusers.app.presentation.di.domain

import com.example.githubusers.domain.repository.UserRepository
import com.example.githubusers.domain.usecase.GetAllUsers
import com.example.githubusers.domain.usecase.GetUserDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetAllUsers(userRepository: UserRepository): GetAllUsers {
        return GetAllUsers(userRepository = userRepository)
    }

    @Provides
    fun provideGetUserDetail(userRepository: UserRepository): GetUserDetail {
        return GetUserDetail(userRepository = userRepository)
    }
}