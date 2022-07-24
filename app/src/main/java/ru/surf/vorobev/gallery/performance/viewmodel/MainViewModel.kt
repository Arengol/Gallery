package ru.surf.vorobev.gallery.performance.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.surf.vorobev.gallery.data.dto.LikedPostDTO
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.data.dto.UserDTO
import ru.surf.vorobev.gallery.network.ResultWrapper
import ru.surf.vorobev.gallery.performance.fragment.LoginFragment

interface MainViewModel {
    fun isAuthorized()
    fun login(phone: String, password: String, context: Context)
    fun logout()
    fun deleteLikedPostReq(id: Int)
    fun getLikedPost()
    fun addLikedPost(id: Int, context: Context)
    fun deleteLikedPost()
    fun getPost()
    fun clearState()
    val state: LiveData<UiState>
    val userData: LiveData<UserDTO>
    val postData: LiveData<List<PostDTO>>
    val likedPostData: LiveData<List<LikedPostDTO>>
}