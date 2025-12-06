package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.response.UserListResponseDto

interface OpenApiDataSource {
    suspend fun getUsers(
        page: Int
    ): UserListResponseDto
}