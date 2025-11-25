package com.sopt.dive.presentation.signup

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.model.UserModel

@Immutable
data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val registerState: UiState<UserModel> = UiState.Loading
)