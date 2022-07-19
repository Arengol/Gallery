package ru.surf.vorobev.gallery.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.surf.vorobev.gallery.data.entity.LikedPostEntity

@Database(entities = [LikedPostEntity::class], version = 1)
@TypeConverters(DateConverter::class, URIConverter::class)
abstract class LikedPostDatabase: RoomDatabase() {
    abstract fun likedPostDAO(): LikedPostDAO
}