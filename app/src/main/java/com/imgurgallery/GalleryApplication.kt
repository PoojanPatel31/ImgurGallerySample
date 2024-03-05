package com.imgurgallery

import android.app.Application
import android.content.Context
import com.imgurgallery.managers.WorkManager

class GalleryApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        WorkManager.setupWorker(appContext)
    }
}