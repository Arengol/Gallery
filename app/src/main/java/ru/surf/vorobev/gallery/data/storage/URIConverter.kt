package ru.surf.vorobev.gallery.data.storage

import android.net.Uri
import androidx.room.TypeConverter

class URIConverter {
    @TypeConverter
    fun toString(uri: Uri): String = uri.toString()

    @TypeConverter
    fun toUri(rawString: String): Uri = Uri.parse(rawString)
}