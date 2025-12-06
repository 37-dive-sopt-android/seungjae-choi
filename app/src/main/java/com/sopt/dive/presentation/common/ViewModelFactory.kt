package com.sopt.dive.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.RepositoryModule
import com.sopt.dive.presentation.home.HomeViewModel
import com.sopt.dive.presentation.mypage.MyPageViewModel
import com.sopt.dive.presentation.signin.SignInViewModel
import com.sopt.dive.presentation.signup.SignUpViewModel

class ViewModelFactory(
    private val userManager: UserManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(RepositoryModule.authRepository, userManager) as T
            }

            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(userManager, RepositoryModule.userRepository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(RepositoryModule.openApiRepository) as T
            }

            modelClass.isAssignableFrom(MyPageViewModel::class.java) -> {
                MyPageViewModel(userManager, RepositoryModule.userRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}