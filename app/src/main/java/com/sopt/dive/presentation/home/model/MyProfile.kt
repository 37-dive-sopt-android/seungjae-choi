package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyProfile(
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int
)