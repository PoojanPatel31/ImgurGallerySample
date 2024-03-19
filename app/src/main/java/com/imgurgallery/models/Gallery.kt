package com.imgurgallery.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery")
data class Gallery(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    @ColumnInfo(name = "timestamp")
    val datetime: Long = System.currentTimeMillis(),
)
