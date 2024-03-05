package com.imgurgallery.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imgurgallery.GalleryApplication
import com.imgurgallery.db.repo.ImageRepository

class GalleryDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryDetailViewModel(ImageRepository(GalleryApplication.appContext)) as T
    }
}