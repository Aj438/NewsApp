package com.android.newsapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.android.newsapp.ui.navigations.MainNavigation
import com.android.newsapp.ui.screens.home.HomeViewModel
import com.android.newsapp.ui.theme.NewsAppTheme

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel> { HomeViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                 MainNavigation(
                    navController = rememberNavController(),
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}
