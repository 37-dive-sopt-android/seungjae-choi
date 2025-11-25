package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.model.LoginModel

@Immutable
data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val loginState: UiState<LoginModel> = UiState.Loading
)