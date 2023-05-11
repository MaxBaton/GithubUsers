package com.example.githubusers.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppRetrofit {
    private const val BASE_URL = "https://api.github.com"
    private var INSTANCE: Retrofit? = null
    // OkHttp
    private const val CONNECT_TIMEOUT: Long = 5
    private const val READ_TIMEOUT: Long = 8

    @kotlin.jvm.Throws(Exception::class)
    fun getInstance(): Retrofit {
        if (INSTANCE == null) {
            synchronized(AppRetrofit::class) {
                if (INSTANCE == null) {
                    val interceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }

                    val okHttpClient = OkHttpClient().newBuilder()
                        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(interceptor = interceptor)
                        .build()

                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build()
                }
            }
        }
        return INSTANCE ?: throw Exception("Error creating Retrofit instance")
    }
}