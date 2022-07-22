package ru.surf.vorobev.gallery.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.surf.vorobev.gallery.data.dto.LikedPostDTO
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.data.dto.UserDTO
import ru.surf.vorobev.gallery.data.storage.LikedPostDAO
import ru.surf.vorobev.gallery.data.storage.LikedPostStorage
import ru.surf.vorobev.gallery.data.storage.UserInformationStorage
import ru.surf.vorobev.gallery.network.*
import ru.surf.vorobev.gallery.network.response.PictureResponse
import ru.surf.vorobev.gallery.util.*
import java.util.*
import javax.inject.Inject

class MainRepository (val likedPostStorage: LikedPostStorage,
                      val userInformationStorage: UserInformationStorage,
                      val networkRepository: NetworkRepository) {


    suspend fun isLogin(): Boolean = userInformationStorage.getToken()?.let{true} ?: false

    suspend fun login(phone: String, password: String, context: Context):ResultWrapper<UserDTO>{
        val response = networkRepository.login(phone, password)
        return when(response){
            is ResultWrapper.Success ->  {
                response.value.userInfo.avatar?.let{savePhoto(response.value.userInfo.avatar, context)}
                userInformationStorage.insert(getUserInfoEntity(response.value))
                ResultWrapper.Success(getUserDTO(response.value))
            }
            is ResultWrapper.LoginFailed -> ResultWrapper.LoginFailed
            is ResultWrapper.UnAuthorized -> ResultWrapper.UnAuthorized
            is ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }
    suspend fun getUserInfo(): UserDTO? {
        val result = userInformationStorage.get()
       return result?.let {getUserDTO(result)}
    }

    suspend fun getPosts():ResultWrapper<List<PostDTO>> {
        val response = networkRepository.getPosts(userInformationStorage.getToken())
        return when(response){
            is ResultWrapper.Success -> {
                val result = mutableListOf<PostDTO>()
                response.value.forEach { result.add(getPostDTO(it)) }
                ResultWrapper.Success(result)
            }
            is ResultWrapper.LoginFailed -> ResultWrapper.LoginFailed
            is ResultWrapper.UnAuthorized -> ResultWrapper.UnAuthorized
            is ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }

    suspend fun addLikedPost(likedPost: PostDTO, context: Context){
        savePhoto(likedPost.photoUrl, context)
        likedPostStorage.insert(getLikedPostEntity(likedPost, Date()))
    }

    suspend fun deleteLikedPost(likedPost: PostDTO){
        likedPostStorage.delete(getLikedPostEntity(likedPost, Date()))
    }

    suspend fun getLikedPost():List<LikedPostDTO>{
        val result = mutableListOf<LikedPostDTO>()
        likedPostStorage.get().forEach { result.add(getLikedPostDTO(it)) }
        return result
    }

    suspend fun logout():ResultWrapper<Unit>{
        val response = networkRepository.logout(userInformationStorage.getToken())
        return when(response){
            is ResultWrapper.Success -> {
                userInformationStorage.delete()
                likedPostStorage.deleteAll()
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.LoginFailed -> ResultWrapper.LoginFailed
            is ResultWrapper.UnAuthorized -> {
                userInformationStorage.delete()
                likedPostStorage.deleteAll()
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }
}
