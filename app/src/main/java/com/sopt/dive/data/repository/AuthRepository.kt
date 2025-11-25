package com.sopt.dive.data.repository

import com.sopt.dive.data.model.LoginModel
import com.sopt.dive.data.model.LoginRequestModel

interface AuthRepository {
    suspend fun postLogin(
        request: LoginRequestModel
    ): Result<LoginModel>
}