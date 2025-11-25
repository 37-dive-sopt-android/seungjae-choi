package com.sopt.dive.presentation.mypage

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.mypage.model.MyPageProfileUiModel
import com.sopt.uniqlo.core.util.UiState

@Immutable
data class MyPageUiState(
    val profileState: UiState<MyPageProfileUiModel> = UiState.Loading
)