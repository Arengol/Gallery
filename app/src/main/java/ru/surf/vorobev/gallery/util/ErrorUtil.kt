package ru.surf.vorobev.gallery.util

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.surf.vorobev.gallery.network.ResultWrapper
import ru.surf.vorobev.gallery.network.response.ErrorResponse
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T>{
   return withContext(dispatcher){
       try {
           ResultWrapper.Success(apiCall.invoke())
       } catch (error: Throwable){
           when (error){
               is IOException -> ResultWrapper.NetworkError
               is HttpException -> {
                   when (error.code()){
                       400 -> ResultWrapper.LoginFailed
                       401 -> ResultWrapper.UnAuthorized
                       else -> ResultWrapper.NetworkError
                   }
               }
               else -> ResultWrapper.NetworkError
           }
       }
   }
}
