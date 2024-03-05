package com.imgurgallery.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.imgurgallery.models.Gallery
import com.imgurgallery.models.GalleryImages
import com.imgurgallery.models.Image
import com.imgurgallery.models.ImageTypeEnum
import java.lang.reflect.Type

class GalleryResponseMapper : JsonDeserializer<List<GalleryImages>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<GalleryImages> {
        return json.asJsonObject.getAsJsonArray("data").mapNotNull {
            val galleryJson = it.asJsonObject
            val gallery = Gallery(
                galleryJson.get("id").asString,
                galleryJson.get("title").asString,
                galleryJson.get("datetime").asLong
            )
            val imageElement = galleryJson.getAsJsonArray("images") ?: return@mapNotNull null

            val images = imageElement.filter { element ->
                ImageTypeEnum.IMAGE.types.contains(element.asJsonObject.get("type").asString)
            }.map { element ->
                Image(
                    element.asJsonObject.get("id").asString,
                    gallery.id,
                    element.asJsonObject.get("link").asString
                )
            }

            if (images.isEmpty()) return@mapNotNull null
            GalleryImages(gallery, images)
        }
    }
}

