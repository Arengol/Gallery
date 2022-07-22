package ru.surf.vorobev.gallery.data.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LikedPostEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    //TODO !!! check
    @ColumnInfo(name = "photoUrl") val photoUrl: String,
    @ColumnInfo(name = "publicationDate") val publicationDate: Long,
    @ColumnInfo(name = "likedDate") val likedDate: Long
)