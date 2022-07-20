package ru.surf.vorobev.gallery.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestBody (
    @Json(name = "phone") val phone: String,
    @Json(name = "password") val password: String
        )