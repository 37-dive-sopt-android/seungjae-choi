package com.sopt.dive.presentation.mypage

import androidx.compose.runtime.Immutable
import com.sopt.dive.data.dto.response.UserDetailResponseDto

@Immutable
data class MyPageUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val profile: UserDetailResponseDto? = null
)