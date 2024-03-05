package com.imgurgallery.modules

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.imgurgallery.BuildConfig
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.network.GalleryResponseMapper
import com.imgurgallery.network.HTTPInterceptor
import com.imgurgallery.network.RestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getAPI(): RestAPI {

        //Prepare a OkHttpClient with request/response interceptor.
        fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .callTimeout(Duration.ofSeconds(180))
                .readTimeout(Duration.ofSeconds(180))
                .writeTimeout(Duration.ofSeconds(180))
                .connectTimeout(Duration.ofSeconds(180))
                .addInterceptor(HTTPInterceptor())
                .build()
        }

        return Retrofit.Builder()
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
    }

}