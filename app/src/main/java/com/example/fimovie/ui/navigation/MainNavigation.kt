package com.example.fimovie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.bookmark_movies.BookmarkMovieScreen
import com.example.fimovie.ui.movies.MovieScreen

@Composable
fun MainNavigation(viewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MovieScreen(navController, viewModel)
        }
        composable(route = Screen.BookmarkScreen.route){
            BookmarkMovieScreen(viewModel = viewModel)
        }
    }
}