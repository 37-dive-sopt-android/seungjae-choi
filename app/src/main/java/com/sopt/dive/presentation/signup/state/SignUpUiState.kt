package com.sopt.dive.presentation.signup.state

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

sealed interface SignUpSideEffect {
    data class ShowToast(val msg: String) : SignUpSideEffect
}