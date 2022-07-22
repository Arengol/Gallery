package ru.surf.vorobev.gallery.util


import ru.surf.vorobev.gallery.data.dto.LikedPostDTO
import ru.surf.vorobev.gallery.data.dto.PostDTO
import ru.surf.vorobev.gallery.data.dto.UserDTO
import ru.surf.vorobev.gallery.data.entity.LikedPostEntity
import ru.surf.vorobev.gallery.data.entity.UserInformationEntity
import ru.surf.vorobev.gallery.network.response.AuthInfoResponse
import ru.surf.vorobev.gallery.network.response.PictureResponse
import java.util.*

fun getUserInfoEntity(response: AuthInfoResponse): UserInformationEntity =
    UserInformationEntity(
        id = response.userInfo.id.toInt(),
        phone = response.userInfo.phone,
        email = response.userInfo.email,
        city = response.userInfo.city,
        firstName = response.userInfo.firstName,
        lastName = response.userInfo.lastName,
        avatar = response.userInfo.avatar,
        about = response.userInfo.about,
        token = response.token
    )

fun getUserDTO(response: AuthInfoResponse): UserDTO =
    UserDTO(
        id = response.userInfo.id.toInt(),
        phone = response.userInfo.phone,
        email = response.userInfo.email,
        city = response.userInfo.city,
        firstName = response.userInfo.firstName,
        lastName = response.userInfo.lastName,
        avatar = response.userInfo.avatar,
        about = response.userInfo.about,
        token = response.token
    )

fun getUserDTO(entity: UserInformationEntity): UserDTO =
    UserDTO(
        id = entity.id,
        phone = entity.phone,
        email = entity.email,
        city = entity.city,
        firstName = entity.firstName,
        lastName = entity.lastName,
        avatar = entity.avatar,
        about = entity.about,
        token = entity.token
    )

fun getPostDTO(response: PictureResponse): PostDTO =
    PostDTO(
        id = response.id.toInt(),
        title = response.title,
        content = response.content,
        photoUrl = response.photoUrl,
        publicationDate = response.publicationDate,
        liked = false
    )
fun getPostDTO(entity: LikedPostEntity): PostDTO =
    PostDTO(
        id = entity.id,
        title = entity.title,
        content = entity.content,
        photoUrl = entity.photoUrl,
        publicationDate = entity.publicationDate,
        liked = true
    )

fun getLikedPostEntity(likedPostDTO: PostDTO, date: Date): LikedPostEntity =
    LikedPostEntity(
        id = likedPostDTO.id,
        title = likedPostDTO.title,
        content = likedPostDTO.content,
        photoUrl = likedPostDTO.photoUrl,
        publicationDate = likedPostDTO.publicationDate,
        likedDate = date.time
    )

fun getLikedPostDTO(entity: LikedPostEntity): LikedPostDTO =
    LikedPostDTO(
        id = entity.id,
        title = entity.title,
        content = entity.content,
        photoUrl = entity.photoUrl,
        publicationDate = entity.publicationDate,
        liked = true,
        likedDate = entity.likedDate
    )


