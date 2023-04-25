package com.example.githubusers.app.presentation.di.data

import com.example.githubusers.data.database.AppDatabase
import com.example.githubusers.data.database.user.UserDao
import com.example.githubusers.data.database.user.UserDetailDao
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
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideUserDetailDao(db: AppDatabase): UserDetailDao {
        return db.userDetailDao()
    }

    @Provides
    @Singleton
    @UserDbStorage
    fun provideUserDbStorage(userDao: UserDao, userDetailDao: UserDetailDao): UserStorage {
        return com.example.githubusers.data.repository.storage.UserDbStorage(
            userDao = userDao,
            userDetailDao = userDetailDao
        )
    }

    @Provides
    @Singleton
    @com.example.githubusers.app.presentation.di.data.UserNetworkStorage
    fun provideUserNetworkStorage(userApi: UserApi): UserStorage {
        return UserNetworkStorage(userApi = userApi)
    }

    @Provides
    @Singleton
    @UserNetworkRepository
    fun provideUserNetworkRepository(
        @com.example.githubusers.app.presentation.di.data.UserNetworkStorage
        userStorage: UserStorage
    ): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }

    @Provides
    @Singleton
    @UserDbRepository
    fun provideUserDbRepository(
        @com.example.githubusers.app.presentation.di.data.UserDbStorage
        userStorage: UserStorage
    ): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }
}