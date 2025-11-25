package com.sopt.dive.data.model

import com.sopt.dive.data.dto.request.RegisterRequestDto

data class RegisterRequestModel(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val age: Int
)

fun RegisterRequestModel.toDto() =
    RegisterRequestDto(
        username = this.username,
        password = this.password,
        name = this.name,
        email = this.email,
        age = this.age
    )