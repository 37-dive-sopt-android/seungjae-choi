package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.LoginResponseDto

interface AuthDataSource {
    suspend fun postLogin(
        request: LogInRequestDto
    ): BaseResponseDto<LoginResponseDto>
}