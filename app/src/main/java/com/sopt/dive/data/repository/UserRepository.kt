package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.response.UserDetailResponseDto
import com.sopt.dive.data.service.UserService

class UserRepository(private val userService: UserService) {
    suspend fun getUser(userId: Long): Result<UserDetailResponseDto> = runCatching {
        val response = userService.getUser(userId)

        if (response.success) {
            response.data ?: throw IllegalStateException()
        } else {
            throw RuntimeException(response.message)
        }
    }
}