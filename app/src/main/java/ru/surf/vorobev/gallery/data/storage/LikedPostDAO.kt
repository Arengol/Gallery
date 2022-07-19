package ru.surf.vorobev.gallery.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.surf.vorobev.gallery.data.entity.LikedPostEntity
import ru.surf.vorobev.gallery.data.entity.UserInformationEntity

@Dao
interface LikedPostDAO {
    @Query("SELECT * FROM likedpostentity")
    fun getAll(): List<LikedPostEntity>


    @Insert
    fun insert(post: LikedPostEntity)

    @Query("DELETE FROM likedpostentity")
    fun deleteAll()

    @Delete
    fun delete(post: LikedPostEntity)

    @Query("SELECT id FROM likedpostentity")
    fun getAllId(): List<Int>
}