package ru.surf.vorobev.gallery.domain

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.surf.vorobev.gallery.data.entity.UserInformationEntity
import ru.surf.vorobev.gallery.data.storage.UserInformationDatabase

class UserInformationStorage (context: Context) {
    private val UserInfoDatabase = Room.databaseBuilder(context, UserInformationDatabase::class.java, "userinformation").build()
    private val dao = UserInfoDatabase.userInformationDAO()

    suspend fun get(): UserInformationEntity = withContext(Dispatchers.IO){
        dao.get()
    }
    //TODO зменить параметры на объекты распарсенных JSON response и добавить сохранение фото через glide
    suspend fun insert(userInformation: UserInformationEntity) = withContext(Dispatchers.IO){
        dao.insert(userInformation)
    }

    suspend fun delete(userInformation: UserInformationEntity) = withContext(Dispatchers.IO){
        dao.delete()
    }

    suspend fun isEmpty() : Boolean  = withContext(Dispatchers.IO){
        dao.get()?.let { false } ?: true
    }
}