package com.practice.finenote.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor() : Interceptor {
    @Inject
    lateinit var tokenManager : TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val authToken =tokenManager.getToken()
        request.addHeader("Authorization",authToken!!)
        return chain.proceed(request.build())
    }
}