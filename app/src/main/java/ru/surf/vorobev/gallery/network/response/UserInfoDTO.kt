package ru.surf.vorobev.gallery.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class UserInfoDTO (
    @Json(name = "id") val id: String,
    @Json(name = "phone") val phone: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "city") val city: String?,
    @Json(name = "about") val about: String
    )