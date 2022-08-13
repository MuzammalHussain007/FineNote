package com.practice.finenote.di

import com.practice.finenote.api.UserAPI
import com.practice.finenote.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val logger : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp : OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun  manageService(retrofit: Retrofit) : UserAPI {
        return retrofit.create(UserAPI::class.java)
    }
}