package com.practice.finenote.di

import android.content.Context
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.api.UserAPI
import com.practice.finenote.utils.Constants.BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
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
    fun provideRetrofit( @ApplicationContext context: Context) : Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient(context).build())
            .baseUrl(BASE_URL)
    }


    private fun getLogging() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun getHttpClient(  context : Context) : OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(getLogging()).addInterceptor(ChuckInterceptor(context))
    }
    @Singleton
    @Provides
    fun  manageServiceUser(retrofit: Retrofit.Builder) : UserAPI {
        return retrofit.build().create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun  manageServiceNote(retrofit: Retrofit.Builder) : NoteAPI {
        return retrofit.build().create(NoteAPI::class.java)
    }
}