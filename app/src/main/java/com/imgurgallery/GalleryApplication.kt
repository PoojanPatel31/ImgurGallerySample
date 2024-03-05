package com.imgurgallery

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.imgurgallery.db.repo.GalleryRepository
import com.imgurgallery.managers.WorkManager
import com.imgurgallery.worker.GalleryWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GalleryApplication : Application(), Configuration.Provider {

    companion object {
        lateinit var appContext: Context
    }

    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(customWorkerFactory).build()

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        WorkManager.setupWorker(appContext)
    }

    class CustomWorkerFactory @Inject constructor(private val repo: GalleryRepository) :
        WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker = GalleryWorker(appContext, workerParameters, repo)

    }
}