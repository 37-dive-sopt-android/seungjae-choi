package com.sopt.dive.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.repository.OpenApiRepository
import com.sopt.dive.presentation.home.HomeUiState
import com.sopt.dive.presentation.home.model.MyProfile
import com.sopt.dive.presentation.home.model.toFriendUiModelList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val openApiRepository: OpenApiRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(myProfile = myProfileData))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadFriendList()
    }

    fun loadFriendList() {
        viewModelScope.launch {
            _uiState.update { it.copy(friendListState = UiState.Loading) }

            openApiRepository.getUsers(page = 2)
                .onSuccess { friendListModel ->
                    val friendUiModels = friendListModel.toFriendUiModelList()

                    _uiState.update {
                        it.copy(
                            friendListState = UiState.Success(friendUiModels)
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            friendListState = UiState.Failure(throwable.message ?: "친구 목록을 불러오는데 실패했습니다.")
                        )
                    }
                }
        }
    }
}

private val myProfileData = MyProfile(
    name = "최승재",
    statusMessage = "안드 어렵다..",
    profileImageRes = R.drawable.profile
)