package com.imgurgallery.modules

import android.app.Application
import com.imgurgallery.db.DatabaseFactory
import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.db.dao.ImageItemDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun getGalleryDao(application: Application): GalleryItemDAO {
        return DatabaseFactory().getDB(application).galleryItemDao()
    }

    @Provides
    @Singleton
    fun getImageDao(application: Application): ImageItemDAO {
        return DatabaseFactory().getDB(application).getImageItemDao()
    }
}