package com.imgurgallery.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.imgurgallery.BR
import com.imgurgallery.databinding.DetailActivityBinding

class GalleryDetailActivity : ComponentActivity() {

    companion object {
        const val GALLERY_KEY = "gallery_item_id"
    }

    private val binding: DetailActivityBinding by lazy {
        DetailActivityBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: GalleryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.extras?.getString(GALLERY_KEY)?.let {
            viewModel = ViewModelProvider(
                this,
                GalleryDetailViewModelFactory()
            )[GalleryDetailViewModel::class.java]

            viewModel.setGalleryId(it)
        } ?: run {
            finish()
            return
        }

        binding.setVariable(BR.viewModel, viewModel)

        viewModel.getImages()
        viewModel.imageList.observe(this) {
            if (!it.isNullOrEmpty()) {
                binding.imageList.visibility = View.VISIBLE
                binding.errorMessage.visibility = View.GONE
                viewModel.loadDataInList(it)
            } else {
                binding.imageList.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
                binding.errorMessage.text = "No images"
            }
        }
    }
}