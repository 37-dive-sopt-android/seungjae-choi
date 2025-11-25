package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.model.RegisterRequestModel
import com.sopt.dive.data.model.UserModel
import com.sopt.dive.data.model.toDto
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun postRegister(request: RegisterRequestModel): Result<UserModel> =
        suspendRunCatching {
            userDataSource.postSignUp(request = request.toDto()).data!!.toModel()
        }

    override suspend fun getUser(id: Long): Result<UserModel> =
        suspendRunCatching {
            userDataSource.getUser(id = id).data!!.toModel()
        }
}