package com.imgurgallery.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgurgallery.db.repo.ImageRepository
import com.imgurgallery.models.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(private val repo: ImageRepository) : ViewModel() {

    private val _imageList = MutableStateFlow<List<Image>>(emptyList())
    val imageList: StateFlow<List<Image>>
        get() = _imageList

    fun setGalleryId(galleryId: String) {
        viewModelScope.launch {
            repo.getAllGalleryImages(galleryId).flowOn(Dispatchers.IO).collect {
                _imageList.emit(it)
            }
        }
    }
}