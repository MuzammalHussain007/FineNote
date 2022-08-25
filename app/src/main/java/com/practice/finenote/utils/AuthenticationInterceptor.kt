package com.practice.finenote.utils

import android.util.Log
import com.practice.finenote.utils.Constants.TAG
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Tag
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor() : Interceptor {
    @Inject
    lateinit var tokenManager : TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val authToken =tokenManager.getToken()
        request.addHeader("Authorization","Bearer "+authToken!!)
        return chain.proceed(request.build())
    }
}