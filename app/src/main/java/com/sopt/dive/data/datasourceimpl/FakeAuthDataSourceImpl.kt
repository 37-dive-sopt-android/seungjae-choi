package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.dto.request.LogInRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.LoginResponseDto

class FakeAuthDataSourceImpl : AuthDataSource {
    override suspend fun postLogin(request: LogInRequestDto): BaseResponseDto<LoginResponseDto> {

        return if (request.username == "seungjae" && request.password == "seungjae1234!") {
            // 성공 응답
            BaseResponseDto(
                success = true,
                code = "200",
                message = "로그인 성공 (Fake)",
                data = LoginResponseDto(
                    userId = 10L,
                    message = "로그인 성공 (Fake)"
                )
            )
        } else {
            // 실패 응답
            BaseResponseDto(
                success = false,
                code = "400",
                message = "아이디 또는 비밀번호가 올바르지 않습니다. (Fake)",
                data = null
            )
        }
    }
}
