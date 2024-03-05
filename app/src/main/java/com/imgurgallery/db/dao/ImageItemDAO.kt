package com.imgurgallery.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.imgurgallery.models.Image

@Dao
interface ImageItemDAO {
    @Query("SELECT * FROM image WHERE gallery_id = :galleryId")
    suspend fun getGalleryImages(galleryId: String): List<Image>
}