package ru.surf.vorobev.gallery.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthInfoDTO (
    @Json(name = "token") val token: String,
    @Json(name = "user_info") val userInfo: UserInfoDTO
        )