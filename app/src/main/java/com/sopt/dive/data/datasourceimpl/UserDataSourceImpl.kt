package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto
import com.sopt.dive.data.service.UserService

class UserDataSourceImpl(
    private val userService: UserService
) : UserDataSource {
    override suspend fun postSignUp(request: RegisterRequestDto): BaseResponseDto<UserDetailResponseDto> =
        userService.postRegister(registerRequestDto = request)

    override suspend fun getUser(id: Long): BaseResponseDto<UserDetailResponseDto> =
        userService.getUser(id = id)
}