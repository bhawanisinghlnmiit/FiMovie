package com.example.fimovie.ui.bookmark_movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.theme.BackgroundColor

@Composable
fun BookmarkMovieScreen(
    viewModel: MovieViewModel
){
    Surface(modifier = Modifier.fillMaxSize(), color = BackgroundColor) {
        BookmarkMovieList(viewModel = viewModel)
    }
}