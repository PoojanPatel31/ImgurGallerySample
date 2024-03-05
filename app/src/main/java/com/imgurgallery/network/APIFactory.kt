package com.imgurgallery.network

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.imgurgallery.BuildConfig
import com.imgurgallery.models.GalleryImages
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object APIFactory {

    /**
     * Prepare RestAPI instance.
     */
    val restApi: RestAPI =
        Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        TypeToken.getParameterized(
                            List::class.java,
                            GalleryImages::class.java
                        ).type,
                        GalleryResponseMapper()
                    ).create()
                )
            )
            .build()
            .create(RestAPI::class.java)


    /**
     * Prepare a OkHttpClient with request/response interceptor.
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().callTimeout(Duration.ofSeconds(180))
            .readTimeout(Duration.ofSeconds(180)).writeTimeout(Duration.ofSeconds(180))
            .connectTimeout(Duration.ofSeconds(180)).addInterceptor(HTTPInterceptor()).build()
    }
}