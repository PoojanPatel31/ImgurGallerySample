package com.imgurgallery.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.imgurgallery.models.Gallery
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image

@Dao
interface GalleryItemDAO {
    @Transaction
    @Query("SELECT * FROM gallery ORDER BY timestamp DESC")
    fun getAllGalleries(): LiveData<List<GalleryImages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGallery(gallery: Gallery, images: List<Image>)

    @Query("SELECT COUNT(id) from gallery")
    suspend fun galleryCount(): Long
}