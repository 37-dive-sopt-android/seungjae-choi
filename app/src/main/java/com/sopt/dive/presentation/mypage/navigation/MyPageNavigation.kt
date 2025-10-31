package com.sopt.dive.presentation.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.mypage.MyPageRoute
import kotlinx.serialization.Serializable

@Serializable
data object MyPage : MainTabRoute

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    navigate(MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    paddingValues: PaddingValues,
    navigateToSignIn: () -> Unit
) {
    composable<MyPage> {
        MyPageRoute(
            paddingValues = paddingValues,
            navigateToSignIn = navigateToSignIn
        )
    }
}