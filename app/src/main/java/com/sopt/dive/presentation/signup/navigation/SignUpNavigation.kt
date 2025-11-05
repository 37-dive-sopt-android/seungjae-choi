package com.sopt.dive.presentation.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

@Serializable
data object SignUp : Route

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    navigate(SignUp, navOptions)
}

fun NavGraphBuilder.signUpNavGraph(
    navigateToSignIn: () -> Unit
) {
    composable<SignUp> {
        SignUpRoute(
            onSignUpComplete = navigateToSignIn
        )
    }
}