package com.example.fimovie.ui.navigation

sealed class Screen(val route : String){
    object MainScreen : Screen("main_screen")
    object BookmarkScreen : Screen("bookmark_screen")
}
