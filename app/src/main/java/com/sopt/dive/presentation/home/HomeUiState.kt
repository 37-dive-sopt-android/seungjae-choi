package com.sopt.dive.presentation.home

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.home.model.Friend
import com.sopt.dive.presentation.home.model.MyProfile

@Immutable
data class HomeUiState(
    val myProfile: MyProfile,
    val friendList: List<Friend>
)