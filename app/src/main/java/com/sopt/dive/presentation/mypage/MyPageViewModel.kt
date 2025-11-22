package com.sopt.dive.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val userManager: UserManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        val currentUserIdString = userManager.loadUserData().id
        val userId = currentUserIdString.toLongOrNull()

        if (userId == null || userId <= 0) {
            _uiState.update { it.copy(errorMessage = "유효한 사용자 ID가 없습니다.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            userRepository.getUser(userId)
                .onSuccess { response ->
                    userManager.saveUserData(
                        id = response.id,
                        username = response.username,
                        name = response.name,
                        email = response.email,
                        age = response.age
                    )

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            profile = response,
                            successMessage = "${response.name}님의 프로필을 불러왔습니다."
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "프로필 조회에 실패했습니다."
                        )
                    }
                }
        }
    }

    fun resetMessageState() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }

}