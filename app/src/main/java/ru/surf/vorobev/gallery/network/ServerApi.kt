package ru.surf.vorobev.gallery.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.surf.vorobev.gallery.network.response.AuthInfoDTO
import ru.surf.vorobev.gallery.network.response.PictureDTO

interface ServerApi {
    @POST ("auth/login")
    suspend fun authLogin (@Body body: LoginRequestBody): AuthInfoDTO

    @POST ("auth/logout")
    suspend fun authLogout (@Header("Authorization") token: String)

    @GET ("picture")
    suspend fun get(@Header("Authorization") token: String): List<PictureDTO>
}