package com.sopt.dive.presentation.home.model

sealed class FriendAction {
    data object None : FriendAction()
    data class Music(val title: String) : FriendAction()
    data object Gift : FriendAction()
}

data class MyProfile(
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int
)

data class Friend(
    val id: Int,
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int,
    val isBirthday: Boolean = false,
    val action: FriendAction
)

data class HomeUiState(
    val myProfile: MyProfile,
    val friendList: List<Friend>
)