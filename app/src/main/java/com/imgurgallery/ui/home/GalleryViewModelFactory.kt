package com.imgurgallery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imgurgallery.GalleryApplication
import com.imgurgallery.db.repo.GalleryRepository

class GalleryViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(GalleryRepository(GalleryApplication.appContext)) as T
    }
}