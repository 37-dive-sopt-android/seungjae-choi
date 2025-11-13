package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): BaseResponseDto<UserDetailResponseDto>
}