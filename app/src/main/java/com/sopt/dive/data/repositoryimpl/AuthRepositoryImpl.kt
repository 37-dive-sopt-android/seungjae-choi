package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.model.LoginModel
import com.sopt.dive.data.model.LoginRequestModel
import com.sopt.dive.data.model.toDto
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.uniqlo.core.util.suspendRunCatching

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postLogin(request: LoginRequestModel): Result<LoginModel> =
        suspendRunCatching {
            authDataSource.postLogin(request = request.toDto()).data!!.toModel()
        }
}