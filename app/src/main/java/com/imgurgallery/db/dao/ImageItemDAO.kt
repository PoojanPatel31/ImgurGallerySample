package com.imgurgallery.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.imgurgallery.models.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageItemDAO {
    @Transaction
    @Query("SELECT * FROM image WHERE gallery_id = :galleryId")
    fun getGalleryImages(galleryId: String): Flow<List<Image>>
}