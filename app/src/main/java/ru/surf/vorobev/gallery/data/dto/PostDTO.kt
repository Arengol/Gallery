package ru.surf.vorobev.gallery.data.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class PostDTO (
    val id: Int,
    val title: String,
    val content: String,
    //TODO !!!! check
    val photoUrl: String,
    val publicationDate: Long,
    var liked: Boolean
    )