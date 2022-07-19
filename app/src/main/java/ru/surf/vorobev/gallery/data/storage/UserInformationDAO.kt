package ru.surf.vorobev.gallery.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.surf.vorobev.gallery.data.entity.UserInformationEntity

@Dao
interface UserInformationDAO {
    @Query("SELECT * FROM userinformationentity")
    fun get(): UserInformationEntity

    @Insert
    fun insert(info:UserInformationEntity)

    @Query("DELETE FROM userinformationentity")
    fun delete()
}