package com.imgurgallery.ui.details

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgurgallery.db.repo.ImageRepository
import com.imgurgallery.models.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(private val repo: ImageRepository) : ViewModel() {

    private val adapter: ImageListAdapter = ImageListAdapter()

    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    val isLoading = ObservableBoolean(false)
    val galleryId = ObservableField<String>()

    fun setGalleryId(galleryId: String) = this.galleryId.set(galleryId)

    fun getAdapter(): ImageListAdapter = adapter

    fun loadDataInList(list: List<Image>) {
        adapter.loadData(list)
    }

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            _imageList.postValue(galleryId.get()?.let { repo.getAllGalleryImages(it) })
            isLoading.set(false)
        }
    }
}