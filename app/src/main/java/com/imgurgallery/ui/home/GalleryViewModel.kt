package com.imgurgallery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgurgallery.db.repo.GalleryRepository
import com.imgurgallery.models.GalleryImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class GalleryViewModel @Inject constructor(private val galleryRepo: GalleryRepository) :
    ViewModel() {

    private var _galleryList = MutableStateFlow<List<GalleryImages>>(emptyList())
    val galleryList: StateFlow<List<GalleryImages>>
        get() = _galleryList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _listOrientation = MutableStateFlow(true)
    val listOrientation: StateFlow<Boolean>
        get() = _listOrientation

    init {
        viewModelScope.launch {
            galleryRepo.getGalleries().flowOn(Dispatchers.IO).collect { _galleryList.emit(it) }
        }
        validateIfDataIsCached()
    }

    private fun validateIfDataIsCached() {
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
}