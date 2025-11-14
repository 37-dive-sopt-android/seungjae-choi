package com.sopt.dive.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.presentation.mypage.MyPageViewModel
import com.sopt.dive.presentation.signin.SignInViewModel
import com.sopt.dive.presentation.signup.SignUpViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(authRepository, UserManager(context)) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(authRepository, UserManager(context)) as T
            }
            modelClass.isAssignableFrom(MyPageViewModel::class.java) -> {
                MyPageViewModel(UserManager(context), userRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}