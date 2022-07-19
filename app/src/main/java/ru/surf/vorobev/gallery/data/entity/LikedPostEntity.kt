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
    @ColumnInfo(name = "photoUri") val photoUri: Uri,
    @ColumnInfo(name = "publicationDate") val publicationDate: Date
)