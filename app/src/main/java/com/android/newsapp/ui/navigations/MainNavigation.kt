package com.android.newsapp.ui.navigations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.newsapp.ui.screens.components.LoadErrorPlaceholder
import com.android.newsapp.ui.screens.home.HomeScreen
import com.android.newsapp.ui.screens.home.HomeViewModel
import com.android.newsapp.ui.screens.web.WebViewScreen
import com.android.newsapp.utils.NetworkResult

@Composable
fun MainNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(
            Destination.Home.route,
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) {
            val result by homeViewModel.newsState.collectAsStateWithLifecycle()
            val list by homeViewModel.articleList.collectAsStateWithLifecycle()
            val orderType by homeViewModel.orderType.collectAsStateWithLifecycle()
            when (result) {
                is NetworkResult.Success -> {
                    HomeScreen(
                        listOfItems = list,
                        orderType = orderType,
                        onOrderChange = {homeViewModel.changeOrder()},
                        onNewsDetails = { navController.navigate("${Destination.Web}?url=$it") })
                }

                is NetworkResult.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(75.dp))
                    }
                }

                is NetworkResult.Error -> {
                    LoadErrorPlaceholder(
                        modifier = Modifier.fillMaxSize(),
                        message = (result as NetworkResult.Error).message,
                        onRetry = { homeViewModel.getData() })
                }
            }

        }
        composable(
            route = "${Destination.Web}?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            Surface(modifier = Modifier.fillMaxSize()) {
                WebViewScreen(url = url)
            }
        }
    }
}