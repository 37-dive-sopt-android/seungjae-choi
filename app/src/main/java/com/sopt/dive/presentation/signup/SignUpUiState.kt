package com.sopt.dive.presentation.signup

import androidx.compose.runtime.Immutable
import com.sopt.dive.data.model.UserModel
import com.sopt.uniqlo.core.util.UiState

@Immutable
data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val registerState: UiState<UserModel> = UiState.Loading
)