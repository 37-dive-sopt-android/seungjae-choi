package com.sopt.dive.presentation.main

import androidx.annotation.DrawableRes
import com.sopt.dive.R
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.mypage.navigation.MyPage
import com.sopt.dive.presentation.search.navigation.Search

enum class MainTab(
    @param:DrawableRes val iconRes: Int,
    val route: MainTabRoute,
    val label: String
) {
    HOME(
        iconRes = R.drawable.ic_home,
        route = Home,
        label = "홈"
    ),
    SEARCH(
        iconRes = R.drawable.ic_search,
        route = Search,
        label = "검색"
    ),
    MY_PAGE(
        iconRes = R.drawable.ic_mypage,
        route = MyPage,
        label = "마이페이지"
    );

    companion object {
        fun find(predicate: (MainTab) -> Boolean): MainTab? {
            return entries.find(predicate)
        }

        fun contains(predicate: (MainTab) -> Boolean): Boolean {
            return entries.any(predicate)
        }
    }
}