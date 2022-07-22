package ru.surf.vorobev.gallery.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.surf.vorobev.gallery.network.response.AuthInfoResponse
import ru.surf.vorobev.gallery.network.response.PictureResponse

interface ServerApi {
    @POST ("auth/login")
    suspend fun authLogin (@Body body: LoginRequestBody): AuthInfoResponse

    @POST ("auth/logout")
    suspend fun authLogout (@Header("Authorization") token: String)

    @GET ("picture")
    suspend fun get(@Header("Authorization") token: String): List<PictureResponse>
}