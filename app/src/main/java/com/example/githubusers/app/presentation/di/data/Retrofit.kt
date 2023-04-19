package com.example.githubusers.app.presentation.di.data

import com.example.githubusers.data.network.AppRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Retrofit {
    @Provides
    @Singleton
    fun provideRetrofit(): retrofit2.Retrofit {
        return AppRetrofit.getInstance()
    }
}