package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto
import com.sopt.dive.data.service.AuthService

class AuthRepository(private val authService: AuthService) {
    suspend fun postRegister(request: RegisterRequestDto): Result<UserDetailResponseDto> = runCatching {
        val response = authService.postRegister(request)

        if (response.success) {
            response.data ?: throw IllegalStateException()
        } else {
            throw RuntimeException(response.message)
        }
    }

    suspend fun postLogin(request: LogInRequestDto): Result<LoginResponseDto> = runCatching {
        val response = authService.postLogin(request)

        if (response.success) {
            response.data ?: throw IllegalStateException()
        } else {
            throw RuntimeException(response.message)
        }
    }
}