package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
sealed class FriendAction {
    data object None : FriendAction()
    data class Music(val title: String) : FriendAction()
    data object Gift : FriendAction()
}

@Immutable
data class MyProfile(
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int
)

@Immutable
data class Friend(
    val id: Int,
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int,
    val isBirthday: Boolean = false,
    val action: FriendAction
)

@Immutable
data class HomeUiState(
    val myProfile: MyProfile,
    val friendList: List<Friend>
)