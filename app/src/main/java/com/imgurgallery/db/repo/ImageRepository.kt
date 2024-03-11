package com.imgurgallery.db.repo

import com.imgurgallery.db.dao.ImageItemDAO
import com.imgurgallery.models.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageItemDao: ImageItemDAO) {

    fun getAllGalleryImages(galleryId: String): Flow<List<Image>> =
        imageItemDao.getGalleryImages(galleryId)
}