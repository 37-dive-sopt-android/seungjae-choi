package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.signup.SignIn

class MainActivity : ComponentActivity() {

    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userManager = UserManager(this)

        val startDestination = checkAutoLogin()

        setContent {
            DiveTheme {
                MainScreen(startDestination = startDestination)
            }
        }
    }

    private fun checkAutoLogin(): Any {
        val autoLogin = userManager.isAutoLoginEnabled()
        val userData = userManager.loadUserData()

        return if (autoLogin && userData.id.isNotEmpty() && userData.password.isNotEmpty()) {
            Home
        } else {
            SignIn
        }
    }
}