package ru.surf.vorobev.gallery.network

import ru.surf.vorobev.gallery.network.response.AuthInfoResponse
import ru.surf.vorobev.gallery.network.response.PictureResponse

interface NetworkRepository {
    suspend fun login(phone: String, password: String): ResultWrapper<AuthInfoResponse>
    suspend fun logout(token: String):ResultWrapper<Unit>
    suspend fun getPosts(token: String):ResultWrapper<List<PictureResponse>>
}