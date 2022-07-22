package ru.surf.vorobev.gallery.data.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserDTO (
    val id: Int,
    val phone: String?,
    val email: String?,
    val city: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val about: String?,
    val token: String
    )