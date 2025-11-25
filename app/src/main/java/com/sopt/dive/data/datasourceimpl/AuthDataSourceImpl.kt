package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.service.AuthService

class AuthDataSourceImpl(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postLogin(request: LogInRequestDto): BaseResponseDto<LoginResponseDto> = 
        authService.postLogin(loginRequestDto = request)
}