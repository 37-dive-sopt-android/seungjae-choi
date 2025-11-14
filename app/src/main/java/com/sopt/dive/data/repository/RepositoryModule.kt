package com.sopt.dive.data.repository

import com.sopt.dive.data.remote.ServicePool
import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.service.UserService

object RepositoryModule {
    private val authService: AuthService = ServicePool.authService
    private val userService: UserService = ServicePool.userService

    val authRepository: AuthRepository by lazy {
        AuthRepository(authService)
    }

    val userRepository: UserRepository by lazy {
        UserRepository(userService)
    }

}