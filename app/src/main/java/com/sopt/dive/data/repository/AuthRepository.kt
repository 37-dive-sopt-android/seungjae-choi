package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto
import com.sopt.dive.data.service.AuthService

class AuthRepository(private val authService: AuthService) {
    suspend fun register(request: RegisterRequestDto): Result<UserDetailResponseDto> = runCatching {
        val response = authService.register(request)

        if (response.success) {
            response.data ?: throw IllegalStateException()
        } else {
            throw RuntimeException(response.message)
        }
    }

    suspend fun login(request: LogInRequestDto): Result<LoginResponseDto> = runCatching {
        val response = authService.login(request)

        if (response.success) {
            response.data ?: throw IllegalStateException()
        } else {
            throw RuntimeException(response.message)
        }
    }
}