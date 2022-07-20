package ru.surf.vorobev.gallery.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureDTO (
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String,
    @Json(name = "photoUrl") val photoUrl: String,
    @Json(name = "publicationDate") val publicationDate: Long
        )