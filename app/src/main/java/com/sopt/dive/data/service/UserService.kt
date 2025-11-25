package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.RegisterRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.UserDetailResponseDto
import com.sopt.dive.data.dto.response.UserListResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("/api/v1/users")
    suspend fun postRegister(
        @Body registerRequestDto: RegisterRequestDto
    ): BaseResponseDto<UserDetailResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Long
    ): BaseResponseDto<UserDetailResponseDto>

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 2
    ): UserListResponseDto
}