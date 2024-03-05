package com.imgurgallery.db.repo

import android.content.Context
import com.imgurgallery.db.DatabaseFactory
import com.imgurgallery.db.dao.ImageItemDAO
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageItemDao: ImageItemDAO) {

    suspend fun getAllGalleryImages(galleryId: String) = imageItemDao.getGalleryImages(galleryId)
}