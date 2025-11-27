package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.OpenApiDataSource
import com.sopt.dive.data.dto.response.UserListResponseDto
import com.sopt.dive.data.service.OpenApiService

class OpenApiDataSourceImpl(
    private val openApiService: OpenApiService
) : OpenApiDataSource {
    override suspend fun getUsers(page: Int): UserListResponseDto =
        openApiService.getUsers(page = page)
}