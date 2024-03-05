package com.imgurgallery.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HTTPInterceptor : Interceptor {
    /**
     * Intercept the request to add Authorization code in the header.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID 53b93369b3b606f")
            .build().let { chain.proceed(it) }
    }

}