package com.sopt.dive.presentation.mypage.model

import androidx.compose.runtime.Immutable
import com.sopt.dive.data.model.UserModel

@Immutable
data class MyPageProfileUiModel(
    val name: String,
    val username: String,
    val email: String,
    val age: String,
)

fun UserModel.toMyPageProfileUiModel() =
    MyPageProfileUiModel(
        name = this.name,
        username = this.username,
        email = this.email,
        age = this.age.toString()
    )