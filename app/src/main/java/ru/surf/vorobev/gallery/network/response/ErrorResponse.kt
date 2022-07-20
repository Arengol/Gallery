package ru.surf.vorobev.gallery.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse (
    @Json(name = "code") val code: Int,
    @Json(name = "reason") val reason: String
)