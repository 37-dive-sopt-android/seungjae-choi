package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.Validator
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.model.RegisterRequestModel
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userManager: UserManager,
    private val userRepository: UserRepository,
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
            _uiState.update { it.copy(registerState = UiState.Failure(validationError)) }
            return
        }

        val ageInt = currentState.age.toInt()

        viewModelScope.launch {
            _uiState.update { it.copy(registerState = UiState.Loading) }

            userRepository.postRegister(
                RegisterRequestModel(
                    username = currentState.username, password = currentState.password,
                    name = currentState.name, email = currentState.email, age = ageInt
                )
            ).onSuccess { memberModel ->
                userManager.saveUserData(
                    id = memberModel.id,
                    username = memberModel.username,
                    name = memberModel.name,
                    email = memberModel.email,
                    age = memberModel.age
                )

                _uiState.update { it.copy(
                    registerState = UiState.Success(memberModel)
                ) }
            }.onFailure { throwable ->
                _uiState.update { it.copy(
                    registerState = UiState.Failure(throwable.message ?: "회원가입에 실패했습니다.")
                ) }
            }
        }
    }
}