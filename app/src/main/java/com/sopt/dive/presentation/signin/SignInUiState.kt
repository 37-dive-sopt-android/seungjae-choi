package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable
import com.sopt.dive.data.model.LoginModel
import com.sopt.uniqlo.core.util.UiState

@Immutable
data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val loginState: UiState<LoginModel> = UiState.Loading
)