package com.imgurgallery.db

import android.content.Context
import androidx.room.Room

object DatabaseFactory {
    fun getDB(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database").build()
}