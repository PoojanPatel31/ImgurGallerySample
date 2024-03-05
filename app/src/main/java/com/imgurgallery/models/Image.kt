package com.imgurgallery.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class Image(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val imageId: String = "",
    @ColumnInfo(name = "gallery_id")
    val galleryId: String = "",
    val link: String = "",
    val type: String = "image/jpeg"
)
