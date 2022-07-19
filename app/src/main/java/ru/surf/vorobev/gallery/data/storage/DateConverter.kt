package ru.surf.vorobev.gallery.data.storage

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate (timeStamp: Long): Date = Date(timeStamp)

    @TypeConverter
    fun toTimeStamp (date: Date): Long = date.time
}