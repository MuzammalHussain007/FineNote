package com.practice.finenote.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor : Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
        val token = tokenManager.getToken()
        newRequest.addHeader("Authorization","Bearer $token")
        return chain.proceed(newRequest.build())
    }
}