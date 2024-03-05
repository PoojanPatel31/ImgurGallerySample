package com.imgurgallery.db.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.imgurgallery.db.DatabaseFactory
import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image
import com.imgurgallery.network.APIFactory
import com.imgurgallery.network.RestAPI

class GalleryRepository(context: Context) {

    private val galleryDao: GalleryItemDAO = DatabaseFactory.getDB(context).galleryItemDao()
    private val restApi: RestAPI = APIFactory.restApi

    suspend fun isCached(): Boolean = galleryDao.galleryCount() > 0

    fun getGalleries(): LiveData<List<GalleryImages>> = galleryDao.getAllGalleries()

    suspend fun fetchAndCacheGallery() {
        val galleries = restApi.getGallery()
        galleries.forEach { galleryDao.saveGallery(it.gallery, it.images) }
    }
}