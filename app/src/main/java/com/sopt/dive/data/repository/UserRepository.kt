package com.sopt.dive.data.repository

import com.sopt.dive.data.model.RegisterRequestModel
import com.sopt.dive.data.model.UserModel

interface UserRepository {
    suspend fun postRegister(
        request: RegisterRequestModel
    ): Result<UserModel>

    suspend fun getUser(
        id: Long
    ): Result<UserModel>
}