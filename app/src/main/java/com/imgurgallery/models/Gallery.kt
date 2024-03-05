package com.imgurgallery.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "gallery")
data class Gallery(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    @ColumnInfo(name = "timestamp")
    val datetime: Long = System.currentTimeMillis(),
) : Parcelable
