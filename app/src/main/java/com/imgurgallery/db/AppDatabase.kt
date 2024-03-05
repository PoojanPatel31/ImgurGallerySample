package com.imgurgallery.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.db.dao.ImageItemDAO
import com.imgurgallery.models.Gallery
import com.imgurgallery.models.Image

@Database(entities = [Gallery::class, Image::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun galleryItemDao(): GalleryItemDAO
    abstract fun getImageItemDao(): ImageItemDAO
}