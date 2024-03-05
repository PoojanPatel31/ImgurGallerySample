package com.imgurgallery.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgurgallery.db.repo.GalleryRepository
import com.imgurgallery.models.GalleryImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class GalleryViewModel @Inject constructor(private val galleryRepo: GalleryRepository) :
    ViewModel() {

    private val _galleryList = MutableLiveData<Result<List<GalleryImages>>>()
    val galleryList: LiveData<Result<List<GalleryImages>>> get() = _galleryList

    private val _onGallerySelect = MutableLiveData<String>()
    val onGallerySelect: LiveData<String> get() = _onGallerySelect

    val isLoading = ObservableBoolean()

    private var adapter = GalleryListAdapter {
        _onGallerySelect.value = it.gallery.id
    }
    private val galleryObserver = Observer<List<GalleryImages>> { value ->
        if (value.isNotEmpty()) {
            _galleryList.postValue(Result.success(value))
        } else {
            _galleryList.postValue(Result.failure(Exception("Empty Gallery")))
        }
    }

    init {
        galleryRepo.getGalleries().observeForever(galleryObserver)
    }

    fun getAdapter(): GalleryListAdapter = adapter

    fun setAdapterData(list: List<GalleryImages>) {
        adapter.loadImages(list)
    }

    fun validateIfDataIsCached() {
        viewModelScope.launch(Dispatchers.IO) { if (!galleryRepo.isCached()) fetchGalleryAndCache() }
    }

    /**
     * Fetch Imgur images in background thread and show the images in main thread.
     */
    fun fetchGalleryAndCache() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            galleryRepo.fetchAndCacheGallery()
            isLoading.set(false)
        }
    }

    override fun onCleared() {
        galleryRepo.getGalleries().removeObserver(galleryObserver)
        super.onCleared()
    }
}