package com.sopt.dive.presentation.home.state

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.util.UiState
import com.sopt.dive.presentation.home.model.FriendUiModel
import com.sopt.dive.presentation.home.model.MyProfile
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class HomeUiState(
    val myProfile: MyProfile,
    val friendListState: UiState<ImmutableList<FriendUiModel>> = UiState.Loading
)

sealed interface HomeSideEffect {
    data class ShowToast(val msg: String) : HomeSideEffect
}
