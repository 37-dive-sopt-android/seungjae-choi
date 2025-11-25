package com.sopt.dive.data.model

import com.sopt.dive.data.dto.request.LogInRequestDto

data class LoginRequestModel(
    val username: String,
    val password: String
)

fun LoginRequestModel.toDto() =
    LogInRequestDto(
        username = this.username,
        password = this.password
    )