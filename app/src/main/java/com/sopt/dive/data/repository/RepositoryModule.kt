package com.sopt.dive.data.repository

import com.sopt.dive.data.remote.ServicePool
import com.sopt.dive.data.service.AuthService

object RepositoryModule {
    private val authService: AuthService = ServicePool.authService

    val authRepository: AuthRepository by lazy {
        AuthRepository(authService)
    }
}