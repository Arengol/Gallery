package ru.surf.vorobev.gallery.data.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformationEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "about") val about: String?,
    @ColumnInfo(name = "token") val token: String
)
