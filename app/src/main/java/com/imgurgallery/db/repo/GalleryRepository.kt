package com.imgurgallery.db.repo

import com.google.gson.JsonObject
import com.imgurgallery.db.dao.GalleryItemDAO
import com.imgurgallery.models.Gallery
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image
import com.imgurgallery.models.ImageTypeEnum
import com.imgurgallery.network.RestAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val restApi: RestAPI,
    private val galleryDao: GalleryItemDAO
) {

    suspend fun isCached(): Boolean = galleryDao.galleryCount() > 0

    fun getGalleries(): Flow<List<GalleryImages>> = galleryDao.getAllGalleries()

    suspend fun fetchAndCacheGallery() {
        val galleries = restApi.getGallery()
        if (galleries.isSuccessful) {
            parseGalleryResponse(galleries).let {
                galleryDao.saveGallery(it.first.toList(), it.second.toList())
            }
        }
    }

    private fun parseGalleryResponse(galleries: Response<JsonObject>): Pair<Collection<Gallery>, Collection<Image>> {
        val pair: Pair<MutableCollection<Gallery>, MutableCollection<Image>> =
            Pair(mutableListOf(), mutableListOf())
        galleries.body()?.asJsonObject?.getAsJsonArray("data")?.forEach {
            val galleryJson = it.asJsonObject
            val gallery = Gallery(
                galleryJson.get("id").asString,
                galleryJson.get("title").asString,
                galleryJson.get("datetime").asLong
            )

            val images = galleryJson.getAsJsonArray("images")?.mapNotNull { element ->
                return@mapNotNull if (ImageTypeEnum.IMAGE.types.contains(
                        element.asJsonObject.get("type").asString
                    )
                ) {
                    Image(
                        element.asJsonObject.get("id").asString,
                        gallery.id,
                        element.asJsonObject.get("link").asString
                    )
                } else null
            } ?: return@forEach

            if (images.isEmpty()) return@forEach
            pair.first.add(gallery)
            pair.second.addAll(images)
        }
        return pair
    }
}