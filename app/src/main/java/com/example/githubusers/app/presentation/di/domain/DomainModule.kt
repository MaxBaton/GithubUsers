package com.example.githubusers.app.presentation.di.domain

import com.example.githubusers.app.presentation.di.data.UserDbRepository
import com.example.githubusers.app.presentation.di.data.UserNetworkRepository
import com.example.githubusers.domain.repository.UserRepository
import com.example.githubusers.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetAllUsers(
        @UserNetworkRepository
        userRepository: UserRepository
    ): GetAllUsers {
        return GetAllUsers(userRepository = userRepository)
    }

    @Provides
    fun provideGetUserDetail(
        @UserNetworkRepository
        userRepository: UserRepository
    ): GetUserDetail {
        return GetUserDetail(userRepository = userRepository)
    }

    @Provides
    fun provideSaveUsers(
        @UserDbRepository
        userRepository: UserRepository
    ): SaveUsers {
        return SaveUsers(userRepository = userRepository)
    }

    @Provides
    fun provideGetAllUsersFromDb(
        @UserDbRepository
        userRepository: UserRepository
    ): GetAllUsersFromDb {
        return GetAllUsersFromDb(userRepository = userRepository)
    }

    @Provides
    fun provideDeleteUsersFromDb(
        @UserDbRepository
        userRepository: UserRepository
    ): DeleteUsersFromDb {
        return DeleteUsersFromDb(userRepository = userRepository)
    }
}