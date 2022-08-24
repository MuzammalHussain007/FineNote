package com.practice.finenote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.api.UserAPI
import com.practice.finenote.utils.AuthenticationInterceptor
import com.practice.finenote.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideRetrofit(okhttpClient: OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
     fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
     fun createHttpClient(httpLogging: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(httpLogging)
    }

    @Singleton
    @Provides
    fun manageServiceUser(retrofit: Retrofit.Builder): UserAPI {
        return retrofit.build().create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun manageServiceNote(retrofit: Retrofit.Builder,okhttpClient:OkHttpClient.Builder,authenticationInterceptor: AuthenticationInterceptor): NoteAPI {
        return retrofit.client(okhttpClient
            .addInterceptor(authenticationInterceptor).build())
            .build()
            .create(NoteAPI::class.java)
    }
    @Singleton
    @Provides
    fun chunkerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }
}