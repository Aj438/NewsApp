package com.android.newsapp.ui.navigations

sealed class Destination(val route: String) {
     object Home : Destination("home")
     object Web : Destination("web_view")
}