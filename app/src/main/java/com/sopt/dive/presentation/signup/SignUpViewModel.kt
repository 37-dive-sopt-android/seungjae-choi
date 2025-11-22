package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.Validator
import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.update { it.copy(username = value) }
    }
    fun updatePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }
    fun updateName(value: String) {
        _uiState.update { it.copy(name = value) }
    }
    fun updateEmail(value: String) {
        _uiState.update { it.copy(email = value) }
    }
    fun updateAge(value: String) {
        _uiState.update { it.copy(age = value) }
    }

    fun register() {
        val currentState = _uiState.value

        val validationError = Validator.validateSignUpInputs(
            username = currentState.username,
            password = currentState.password,
            name = currentState.name,
            email = currentState.email,
            ageText = currentState.age
        )

        if (validationError != null) {
            _uiState.update { it.copy(errorMessage = validationError) }
            return
        }

        val ageInt = currentState.age.toInt()

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            authRepository.postRegister(
                RegisterRequestDto(
                    username = currentState.username, password = currentState.password,
                    name = currentState.name, email = currentState.email, age = ageInt
                )
            ).onSuccess { response ->
                userManager.saveUserData(
                    id = response.id,
                    username = response.username,
                    name = response.name,
                    email = response.email,
                    age = response.age
                )

                _uiState.update { it.copy(
                    isLoading = false,
                    isSuccess = true,
                    successMessage = response.username + "님, 회원가입 성공!",
                    errorMessage = null
                ) }
            }.onFailure { throwable ->
                _uiState.update { it.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = throwable.message ?: "회원가입에 실패했습니다.",
                    successMessage = null
                ) }
            }
        }
    }

    fun resetMessageState() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}