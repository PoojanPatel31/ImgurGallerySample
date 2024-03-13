package com.imgurgallery.network

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface RestAPI {

    /**
     * An endpoint to get list of top images of week.
     */
    @GET("3/gallery/top/top/0/day")
    suspend fun getGallery(): Response<JsonObject>

}