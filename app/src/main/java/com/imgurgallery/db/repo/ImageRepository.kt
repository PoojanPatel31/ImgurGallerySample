package com.imgurgallery.db.repo

import android.content.Context
import com.imgurgallery.db.DatabaseFactory
import com.imgurgallery.db.dao.ImageItemDAO

class ImageRepository(context: Context) {

    private val imageItemDao: ImageItemDAO = DatabaseFactory.getDB(context).getImageItemDao()

    suspend fun getAllGalleryImages(galleryId: String) = imageItemDao.getGalleryImages(galleryId)
}