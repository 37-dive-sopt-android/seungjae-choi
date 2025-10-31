package com.sopt.dive.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.home.navigation.homeNavGraph
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.main.component.MainBottomBar
import com.sopt.dive.presentation.mypage.navigation.myPageNavGraph
import com.sopt.dive.presentation.mypage.navigation.navigateToMyPage
import com.sopt.dive.presentation.search.navigation.navigateToSearch
import com.sopt.dive.presentation.search.navigation.searchNavGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Home
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentTab = MainTab.find { tab ->
        currentDestination?.hasRoute(tab.route::class) == true
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                tabs = MainTab.entries.toList(),
                currentTab = currentTab,
                onTabSelected = { tab ->
                    if (tab == currentTab) return@MainBottomBar

                    when (tab) {
                        MainTab.HOME -> navController.navigateToHome(
                            navOptions = navOptions {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                        MainTab.SEARCH -> navController.navigateToSearch(
                            navOptions = navOptions {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                        MainTab.MY_PAGE -> navController.navigateToMyPage(
                            navOptions = navOptions {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 메인 탭 화면
            homeNavGraph(paddingValues)
            searchNavGraph(paddingValues)
            myPageNavGraph(paddingValues)
        }
    }
}