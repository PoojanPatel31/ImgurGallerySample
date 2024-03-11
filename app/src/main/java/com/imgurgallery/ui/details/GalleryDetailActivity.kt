package com.imgurgallery.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryDetailActivity : ComponentActivity() {

    companion object {
        const val GALLERY_KEY = "gallery_item_id"
    }

    private val viewModel: GalleryDetailViewModel by viewModels()

    @ExperimentalGlideComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getString(GALLERY_KEY)?.let {
            viewModel.setGalleryId(it)
        } ?: run {
            finish()
            return
        }
        setContent {
            Surface {
                ImageList()
            }
        }
    }
}