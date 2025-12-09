package com.sopt.dive.data.model

import com.sopt.dive.data.dto.response.UserListResponseDto
import com.sopt.dive.data.dto.response.UserResponseDto

data class FriendListModel(
    val friendList: List<FriendModel>
)

data class FriendModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)

fun UserListResponseDto.toModel() =
    FriendListModel(
        friendList = this.data.map { it.toModel() }
    )

fun UserResponseDto.toModel() =
    FriendModel(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )

