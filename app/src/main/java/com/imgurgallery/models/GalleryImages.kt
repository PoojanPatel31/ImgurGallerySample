package com.imgurgallery.models

import androidx.room.Embedded
import androidx.room.Relation

data class GalleryImages(
    @Embedded
    val gallery: Gallery,
    @Relation(parentColumn = "id", entityColumn = "gallery_id")
    val images: List<Image>
)