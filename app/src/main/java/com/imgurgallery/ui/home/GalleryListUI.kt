package com.imgurgallery.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.imgurgallery.R
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.ui.HomeViewModel
import com.imgurgallery.util.DateTimeUtil

@ExperimentalMaterial3Api
@Composable
fun GalleryList(
    viewModel: HomeViewModel,
    innerPadding: PaddingValues,
    onItemClick: (item: GalleryImages) -> Unit
) {
    val galleryList = viewModel.galleryList.collectAsState()
    val galleryState by remember { galleryList }

    val listOrientation = viewModel.listOrientation.collectAsState()
    val listOrientationState by remember { listOrientation }

    if (galleryState.isNotEmpty()) {
        if (listOrientationState) {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(items = galleryState) {
                    GalleryRow(
                        modifier = Modifier.clickable { onItemClick(it) },
                        item = it
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(items = galleryState) {
                    GalleryRow(
                        modifier = Modifier.clickable { onItemClick(it) },
                        item = it
                    )
                }
            }
        }
    }

    val isLoading by remember { mutableStateOf(viewModel.isLoading.value) }
    val isLoadingState = PullToRefreshState(
        positionalThresholdPx = PullToRefreshDefaults.PositionalThreshold.value,
        initialRefreshing = isLoading
    )
    PullToRefreshContainer(state = isLoadingState)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GalleryRow(modifier: Modifier = Modifier, item: GalleryImages) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .shadow(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .then(modifier)
    ) {
        GlideImage(
            model = item.images.first().link,
            contentDescription = "Imgur Image",
            contentScale = ContentScale.Crop,
            requestBuilderTransform = {
                it.diskCacheStrategy(DiskCacheStrategy.ALL)
            },
            loading = placeholder(R.drawable.loading_svg),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .alpha(0.8f)
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            Text(
                text = item.gallery.title,
                maxLines = 1,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 2.dp)
            )
            Text(
                text = DateTimeUtil.format(item.gallery.datetime),
                modifier = Modifier.padding(0.dp, 2.dp, 0.dp, 0.dp),
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
        if (item.images.size > 1) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = "${item.images.size}",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}