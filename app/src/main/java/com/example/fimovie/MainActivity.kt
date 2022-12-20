package com.example.fimovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.movies.MovieScreen
import com.example.fimovie.ui.theme.BackgroundColor
import com.example.fimovie.ui.theme.FiMovieTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SystemUiScreen()
            FiMovieTheme {
                MovieScreen(movieViewModel)
            }
        }
    }
}

@Composable
fun SystemUiScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = BackgroundColor,
        darkIcons = true
    )
}
