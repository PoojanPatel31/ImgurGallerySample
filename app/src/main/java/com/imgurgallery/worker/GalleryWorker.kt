package com.imgurgallery.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.imgurgallery.db.repo.GalleryRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class GalleryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private var galleryRepository: GalleryRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.e("GalleryWorker", "Fetching and storing in background.")
        galleryRepository.fetchAndCacheGallery()
        Log.e("GalleryWorker", "Fetching and storing in background completed")
        Log.e("GalleryWorker", "-------------------")
        return Result.success()
    }
}