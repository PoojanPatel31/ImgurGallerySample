package com.imgurgallery.db.repo

import androidx.lifecycle.LiveData
import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.network.RestAPI
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val restApi: RestAPI,
    private val galleryDao: GalleryItemDAO
) {

    suspend fun isCached(): Boolean = galleryDao.galleryCount() > 0

    fun getGalleries(): LiveData<List<GalleryImages>> = galleryDao.getAllGalleries()

    suspend fun fetchAndCacheGallery() {
        val galleries = restApi.getGallery()
        galleries.forEach { galleryDao.saveGallery(it.gallery, it.images) }
    }
}