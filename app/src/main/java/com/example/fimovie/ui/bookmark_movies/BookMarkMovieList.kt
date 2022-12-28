package com.example.fimovie.ui.bookmark_movies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fimovie.R
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.presentation.MovieEvent
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.appbar.TitleBar
import com.example.fimovie.ui.movies.MovieItem
import com.example.fimovie.ui.progressbar.CircularProgressBar
import com.example.fimovie.ui.appbar.TitleBar
import com.example.fimovie.ui.theme.BackgroundColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookmarkMovieList(viewModel: MovieViewModel){
    val state = viewModel.bookmarkMovies.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = BackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            TitleBar(
                imageIcon = R.drawable.ic_bookmark_page
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid( columns = GridCells.Fixed(3)) {
                items(state.value) { movie ->
                    BookMarkItem(
                        movie = movie,
                        onDeleteClick = {
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
                    )
                }

            }
        }
    }

}