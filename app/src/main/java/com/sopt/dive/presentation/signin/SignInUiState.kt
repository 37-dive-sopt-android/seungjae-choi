package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)