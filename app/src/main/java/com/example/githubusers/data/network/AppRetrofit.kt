package com.example.githubusers.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {
    companion object {
        private const val BASE_URL = "https://api.github.com"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                synchronized(AppRetrofit::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}