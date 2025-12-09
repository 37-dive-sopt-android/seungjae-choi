package com.sopt.dive.presentation.mypage.state

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.util.UiState
import com.sopt.dive.presentation.mypage.model.MyPageProfileUiModel

@Immutable
data class MyPageUiState(
    val profileState: UiState<MyPageProfileUiModel> = UiState.Loading
)

sealed interface MyPageSideEffect {
    data class ShowToast(val msg: String) : MyPageSideEffect
}
