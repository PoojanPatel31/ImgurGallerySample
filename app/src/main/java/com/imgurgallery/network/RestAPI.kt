package com.imgurgallery.network

import com.imgurgallery.models.Gallery
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image
import retrofit2.http.GET

interface RestAPI {

    /**
     * An endpoint to get list of top images of week.
     */
    @GET("3/gallery/top/top/0/day")
    suspend fun getGallery(): List<GalleryImages>

}