package com.sopt.dive.presentation.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.signin.SignInRoute
import kotlinx.serialization.Serializable

@Serializable
data object SignIn : Route

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    navigate(SignIn, navOptions)
}

fun NavGraphBuilder.signInNavGraph(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<SignIn> {
        SignInRoute(
            onSignUpClick = navigateToSignUp,
            onSignInSuccess = navigateToHome
        )
    }
}