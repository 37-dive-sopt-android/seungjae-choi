package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.Validator
import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.update { it.copy(username = value) }
    }
    fun updatePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun login() {
        val currentState = _uiState.value

        val validationError = Validator.validateSignInInputs(
            username = currentState.username,
            password = currentState.password
        )

        if (validationError != null) {
            _uiState.update { it.copy(errorMessage = validationError) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            authRepository.postLogin(
                LogInRequestDto(username = currentState.username, password = currentState.password)
            ).onSuccess { response ->
                userManager.setUserId(response.userId.toString())
                userManager.setAutoLogin(true)

                _uiState.update { it.copy(
                    isLoading = false,
                    isSuccess = true,
                    successMessage = response.message,
                    errorMessage = null
                ) }
            }.onFailure { throwable ->
                _uiState.update { it.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = throwable.message ?: "알 수 없는 오류가 발생했습니다.",
                    successMessage = null
                ) }
            }
        }
    }

    fun resetMessageState() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}