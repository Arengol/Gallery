package ru.surf.vorobev.gallery.data.dto

data class LikedPostDTO (
    val id: Int,
    val title: String,
    val content: String,
    //TODO !!!! check
    val photoUrl: String,
    val publicationDate: Long,
    val liked: Boolean,
    val likedDate: Long
    )
