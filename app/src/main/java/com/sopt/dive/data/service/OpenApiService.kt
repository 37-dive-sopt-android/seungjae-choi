package com.sopt.dive.data.service

import com.sopt.dive.data.dto.response.UserListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenApiService {
    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 2
    ): UserListResponseDto
}