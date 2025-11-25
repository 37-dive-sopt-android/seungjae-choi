package com.sopt.dive.data.repository

import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.dive.data.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.data.remote.ServicePool
import com.sopt.dive.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl

object RepositoryModule {
    private val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(ServicePool.authService)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authDataSource)
    }

    private val userDataSource: UserDataSource by lazy {
        UserDataSourceImpl(ServicePool.userService)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDataSource)
    }

}