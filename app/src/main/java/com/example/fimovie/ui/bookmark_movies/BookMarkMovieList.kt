package com.example.fimovie.ui.bookmark_movies

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.presentation.MovieEvent
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.appbar.TitleBar
import com.example.fimovie.ui.movies.MovieItem
import com.example.fimovie.ui.progressbar.CircularProgressBar
import com.example.fimovie.ui.appbar.TitleBar
import kotlinx.coroutines.launch

@Composable
fun BookmarkMovieList(viewModel: MovieViewModel){
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    LazyVerticalGrid( columns = GridCells.Fixed(3)) {
        items(state) { movie ->
                BookMarkItem(
                    movie = movie
                ) {
                    viewModel.onEvent(MovieEvent.DeleteMovie(movie))
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Movie deleted",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(MovieEvent.RestoreMovie)
                        }
                    }
                }
        }

    }
}