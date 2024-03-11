package com.imgurgallery.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy

@ExperimentalGlideComposeApi
@Composable
fun ImageList() {
    val viewmodel: GalleryDetailViewModel = hiltViewModel()
    val imageList = viewmodel.imageList.collectAsState()
    LazyColumn {
        items(imageList.value) {
            GlideImage(
                model = it.link,
                contentScale = ContentScale.FillBounds,
                requestBuilderTransform = { requestBuilder ->
                    requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL)
                },
                contentDescription = it.link,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center
            )
        }
    }

}