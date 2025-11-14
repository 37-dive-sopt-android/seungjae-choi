package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.response.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequestDto: LogInRequestDto
    ): BaseResponseDto<LoginResponseDto>

    @POST("/api/v1/users")
    suspend fun register(
        @Body registerRequestDto: RegisterRequestDto
    ): BaseResponseDto<RegisterResponseDto>
}