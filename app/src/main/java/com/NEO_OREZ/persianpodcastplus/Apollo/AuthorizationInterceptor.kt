package com.NEO_OREZ.persianpodcastplus.Apollo

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor (private val context: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", context)
            .build()
        return chain.proceed(request)
    }
}