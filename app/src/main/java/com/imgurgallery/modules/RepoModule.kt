package com.imgurgallery.modules

import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.db.dao.ImageItemDAO
import com.imgurgallery.db.repo.GalleryRepository
import com.imgurgallery.db.repo.ImageRepository
import com.imgurgallery.network.RestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideGalleryRepo(api: RestAPI, galleryDao: GalleryItemDAO): GalleryRepository {
        return GalleryRepository(api, galleryDao)
    }

    @Provides
    @Singleton
    fun provideImageRepo(imageDAO: ImageItemDAO): ImageRepository {
        return ImageRepository(imageDAO)
    }
}