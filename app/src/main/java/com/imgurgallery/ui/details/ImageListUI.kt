package com.imgurgallery.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.imgurgallery.R
import com.imgurgallery.ui.HomeViewModel

@ExperimentalGlideComposeApi
@Composable
fun ImageList(viewmodel: HomeViewModel, paddingValues: PaddingValues) {
    val imageList = viewmodel.imageList.collectAsState()
    val imagesState by remember { imageList }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(imagesState) {
            GlideImage(
                model = it.link,
                contentScale = ContentScale.FillBounds,
                requestBuilderTransform = { requestBuilder ->
                    requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL)
                },
                contentDescription = it.link,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center,
                loading = placeholder(R.drawable.loading_svg)
            )
        }
    }

}