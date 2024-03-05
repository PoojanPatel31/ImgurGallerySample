package com.imgurgallery.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.imgurgallery.db.repo.GalleryRepository

class GalleryWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        Log.e("GalleryWorker", "Fetching and storing in background.")
        val repo = GalleryRepository(context)
        repo.fetchAndCacheGallery()
        Log.e("GalleryWorker", "Fetching and storing in background completed")
        Log.e("GalleryWorker", "-------------------")
        return Result.success()
    }
}