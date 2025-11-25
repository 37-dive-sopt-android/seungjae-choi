package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto

interface UserDataSource {
    suspend fun postSignUp(
        request: RegisterRequestDto
    ): BaseResponseDto<UserDetailResponseDto>

    suspend fun getUser(
        id: Long
    ): BaseResponseDto<UserDetailResponseDto>
}