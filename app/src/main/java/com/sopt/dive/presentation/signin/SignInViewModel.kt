package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.Validator
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.model.LoginRequestModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.presentation.signin.state.SignInSideEffect
import com.sopt.dive.presentation.signin.state.SignInUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect

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
            _uiState.update { it.copy(loginState = UiState.Failure(validationError)) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(loginState = UiState.Loading) }

            authRepository.postLogin(
                LoginRequestModel(
                    username = currentState.username,
                    password = currentState.password
                )
            ).onSuccess { response ->
                userManager.setUserId(response.userId.toString())
                userManager.setAutoLogin(true)

                _uiState.update { it.copy(
                    loginState = UiState.Success(response)
                ) }

                _sideEffect.emit(SignInSideEffect.ShowToast("로그인 성공!"))
            }.onFailure { throwable ->
                val errorMessage = throwable.message ?: "알 수 없는 오류가 발생했습니다."

                _uiState.update { it.copy(
                    loginState = UiState.Failure(errorMessage)
                ) }

                _sideEffect.emit(SignInSideEffect.ShowToast(errorMessage))
            }
        }
    }
}