package com.imgurgallery.managers

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.imgurgallery.worker.GalleryWorker
import java.time.Duration

object WorkManager {

    fun setupWorker(context: Context) {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequestBuilder<GalleryWorker>(Duration.ofMinutes(5))
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(context).cancelAllWork()
//        WorkManager.getInstance(context).enqueue(workerRequest)
    }

}