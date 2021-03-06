package ru.surf.vorobev.gallery.performance.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.surf.vorobev.gallery.data.dto.LikedPostDTO
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.data.dto.UserDTO
import ru.surf.vorobev.gallery.domain.MainRepository
import ru.surf.vorobev.gallery.network.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

class MainViewModelImpl private constructor(private val mainRepository: MainRepository) : MainViewModel, ViewModel() {
    object Builder{
        private var singleton: MainViewModel? = null
        fun create(mainRepository: MainRepository):MainViewModel{
            if(singleton==null) {
                singleton = MainViewModelImpl(mainRepository)
            return singleton!!
            }
            else return singleton!!

        }
    }

    private val _state = MutableLiveData<UiState>(UiState.Wait)
    override val state: LiveData<UiState>
        get() = _state

    private val _userData = MutableLiveData<UserDTO>()
    override val userData: LiveData<UserDTO>
        get() = _userData

    private val _postData = MutableLiveData<List<PostDTO>>()
    override val postData: LiveData<List<PostDTO>>
        get() = _postData

    private val _likedPostData = MutableLiveData<List<LikedPostDTO>>()
    override val likedPostData: LiveData<List<LikedPostDTO>>
        get() = _likedPostData

    private var deleteLikedPostId:Int = 0
    override fun clearState(){
        _state.value=UiState.Wait
    }

    override fun deleteLikedPostReq(id: Int) {
        deleteLikedPostId=id
        _state.value = UiState.DeletLikeRequest
    }

    override fun isAuthorized() {
        viewModelScope.launch {
            val result = mainRepository.getUserInfo()
            _state.value = result?.let {
                _userData.value = it
                UiState.Succes
            } ?: UiState.Unathorized
        }
    }

    override fun login(phone: String, password: String, context: Context) {
        _state.value = UiState.Loading
        viewModelScope.launch {
            _state.value = when(val result = mainRepository.login(phone,password, context)){
                is ResultWrapper.Success -> {
                    _userData.value = result.value
                    UiState.Succes
                }
                is ResultWrapper.LoginFailed -> UiState.InvalidLoginOrPass
                is ResultWrapper.NetworkError -> UiState.DataError
                is ResultWrapper.UnAuthorized -> UiState.DataError
            }
        }
    }

    override fun logout() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            _state.value = when(val result = mainRepository.logout(_userData.value!!.token)){
                is ResultWrapper.Success -> UiState.Unathorized
                is ResultWrapper.UnAuthorized -> UiState.Unathorized
                is ResultWrapper.NetworkError -> UiState.DataError
                is ResultWrapper.LoginFailed -> UiState.DataError
            }
        }
    }

    override fun getPost() {
        _state.value = UiState.Loading
        viewModelScope.launch {
           _state.value = when(val result = mainRepository.getPosts(_userData.value!!.token)){
                is ResultWrapper.Success -> {
                    val id = mainRepository.getLikedPostID()
                    result.value.map { if(id.contains(it.id))it.liked = true }
                    _postData.value = result.value
                        UiState.Succes
                }
                is ResultWrapper.UnAuthorized -> UiState.Unathorized
                is ResultWrapper.NetworkError -> UiState.DataError
                is ResultWrapper.LoginFailed -> UiState.DataError
            }
        }
    }

    override fun getLikedPost() {
        viewModelScope.launch {
            val result = mainRepository.getLikedPost()
            _state.value = if (result.isEmpty()) UiState.DataError
            else {
                _likedPostData.value = result
                UiState.Succes
            }
        }
    }

    override fun addLikedPost(id: Int, context: Context) {
        viewModelScope.launch {
            mainRepository.addLikedPost(_postData.value!!.filter { it.id == id }.first(), context)
        }
    }

    override fun deleteLikedPost() {
        viewModelScope.launch {
            mainRepository.deleteLikedPost(_postData.value!!.filter { it.id == deleteLikedPostId }.first())
        }
    }
}