package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sopt.dive.data.model.FriendListModel
import com.sopt.dive.data.model.FriendModel

@Immutable
sealed class FriendAction {
    data object None : FriendAction()
    data class Music(val title: String) : FriendAction()
    data object Gift : FriendAction()
}

@Immutable
data class FriendUiModel(
    val id: Int,
    val name: String,
    val statusMessage: String,
    val profileImageUrl: String,
    val isBirthday: Boolean = false,
    val action: FriendAction
)

fun FriendListModel.toFriendUiModelList(): List<FriendUiModel> =
    this.friendList.mapIndexed { index, model ->
        model.toFriendUiModel(index)
    }

fun FriendModel.toFriendUiModel(index: Int): FriendUiModel {
    val isBirthday = this.id % 3 == 0
    val action = if (isBirthday) {
        FriendAction.Gift
    } else {
        when (index % 2) {
            0 -> FriendAction.Music("Supernova-aespa")
            else -> FriendAction.None
        }
    }

    return FriendUiModel(
        id = this.id,
        name = "${this.firstName} ${this.lastName}",
        statusMessage = this.email,
        profileImageUrl = this.avatar,
        isBirthday = isBirthday,
        action = action
    )
}