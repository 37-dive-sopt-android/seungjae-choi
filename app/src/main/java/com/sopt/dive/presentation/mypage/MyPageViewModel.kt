package com.sopt.dive.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.presentation.mypage.model.toMyPageProfileUiModel
import com.sopt.dive.presentation.mypage.state.MyPageSideEffect
import com.sopt.dive.presentation.mypage.state.MyPageUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        val currentUserIdString = userManager.loadUserData().id
        val userId = currentUserIdString.toLongOrNull()

        if (userId == null || userId <= 0) {
            _uiState.update { it.copy(profileState = UiState.Failure("유효한 사용자 ID가 없습니다.")) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(profileState = UiState.Loading) }

            userRepository.getUser(userId)
                .onSuccess { userModel ->
                    val uiModel = userModel.toMyPageProfileUiModel()

                    userManager.saveUserData(
                        id = userModel.id,
                        username = userModel.username,
                        name = userModel.name,
                        email = userModel.email,
                        age = userModel.age
                    )

                    _uiState.update {
                        it.copy(
                            profileState = UiState.Success(uiModel)
                        )
                    }

                    _sideEffect.emit(MyPageSideEffect.ShowToast("프로필을 불러왔습니다."))
                }
                .onFailure { throwable ->
                    val errorMessage = throwable.message ?: "프로필 조회에 실패했습니다."

                    _uiState.update {
                        it.copy(
                            profileState = UiState.Failure(errorMessage)
                        )
                    }

                    _sideEffect.emit(MyPageSideEffect.ShowToast(errorMessage))
                }
        }
    }
}