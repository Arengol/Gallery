package ru.surf.vorobev.gallery.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.surf.vorobev.gallery.data.entity.UserInformationEntity

@Database(entities = [UserInformationEntity::class], version = 1)
@TypeConverters(URIConverter::class)
abstract class UserInformationDatabase: RoomDatabase() {
    abstract fun userInformationDAO(): UserInformationDAO
}