package ru.surf.vorobev.gallery.data.storage

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.surf.vorobev.gallery.data.entity.LikedPostEntity

class LikedPostStorage(val context: Context) {
    private val LikedPostDatabase = Room.databaseBuilder(context, LikedPostDatabase::class.java, "likedpost").build()
    private val dao = LikedPostDatabase.likedPostDAO()

    suspend fun get(): List<LikedPostEntity> = withContext(Dispatchers.IO){
        dao.getAll()
    }

    suspend fun insert(post: LikedPostEntity) = withContext(Dispatchers.IO){
        dao.insert(post)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO){
        dao.deleteAll()
    }

    suspend fun delete(post: LikedPostEntity) = withContext(Dispatchers.IO){
        dao.delete(post)
    }

    suspend fun getAllId(): List<Int> = withContext(Dispatchers.IO){
        dao.getAllId()
    }

}