package ru.surf.vorobev.gallery.network

import kotlinx.coroutines.Dispatchers
import ru.surf.vorobev.gallery.network.response.AuthInfoResponse
import ru.surf.vorobev.gallery.network.response.PictureResponse
import ru.surf.vorobev.gallery.util.safeApiCall
import javax.inject.Inject

class NetworkRepositoryImpl (private val serverApi: ServerApi): NetworkRepository {

    override suspend fun login(phone: String, password: String): ResultWrapper<AuthInfoResponse>{
        return safeApiCall(Dispatchers.IO){
            serverApi.authLogin(LoginRequestBody(phone, password))
        }
    }

    override suspend fun logout(token: String):ResultWrapper<Unit>{
        return safeApiCall(Dispatchers.IO){
            serverApi.authLogout("Token ${token}")
        }
    }

    override suspend fun getPosts(token: String):ResultWrapper<List<PictureResponse>>{
        return safeApiCall(Dispatchers.IO){
            serverApi.get("Token ${token}")
        }
    }
}