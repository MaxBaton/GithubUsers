package com.example.githubusers.app.presentation.di.data

import com.example.githubusers.data.network.UserApi
import com.example.githubusers.data.repository.UserRepositoryImpl
import com.example.githubusers.data.repository.storage.UserNetworkStorage
import com.example.githubusers.data.repository.storage.UserStorage
import com.example.githubusers.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserNetworkStorage(userApi: UserApi): UserStorage {
        return UserNetworkStorage(userApi = userApi)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }
}