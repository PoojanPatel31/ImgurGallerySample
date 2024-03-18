package com.imgurgallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgurgallery.db.repo.GalleryRepository
import com.imgurgallery.db.repo.ImageRepository
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val galleryRepo: GalleryRepository,
    private val imageRepo: ImageRepository
) :
    ViewModel() {

    private val _galleryList = MutableStateFlow<List<GalleryImages>>(emptyList())
    val galleryList: StateFlow<List<GalleryImages>>
        get() = _galleryList

    private val _imageList = MutableStateFlow<List<Image>>(emptyList())
    val imageList: StateFlow<List<Image>>
        get() = _imageList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _listOrientation = MutableStateFlow(true)
    val listOrientation: StateFlow<Boolean>
        get() = _listOrientation

    fun validateIfDataIsCached() {
        viewModelScope.launch(Dispatchers.IO) { if (!galleryRepo.isCached()) fetchGalleryAndCache() }
    }

    /**
     * Fetch Imgur images in background thread and show the images in main thread.
     */
    private fun fetchGalleryAndCache() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            galleryRepo.fetchAndCacheGallery()
            _isLoading.value = false
        }
    }

    fun toggleOrientation() {
        _listOrientation.value = !listOrientation.value
    }

    fun fetchGalleryList() {
        viewModelScope.launch {
            galleryRepo.getGalleries().flowOn(Dispatchers.IO).collect {
                _galleryList.emit(it)
            }
        }
    }

    fun setGalleryId(galleryId: String) {
        viewModelScope.launch {
            imageRepo.getAllGalleryImages(galleryId).flowOn(Dispatchers.IO).collect {
                _imageList.emit(it)
            }
        }
    }
}