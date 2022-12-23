package com.example.fimovie.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.model.dto.Search
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.appbar.MainAppBar
import com.example.fimovie.ui.appbar.TitleBar
import com.example.fimovie.ui.filter_options.FilterMovies
import com.example.fimovie.ui.progressbar.CircularProgressBar

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val searchTextState by viewModel.searchTextState
    val searchWidgetState by viewModel.searchWidgetState
    val filterState by viewModel.filterState
    val pagingData = viewModel.getMovie(searchTextState)
    val movies: LazyPagingItems<Search> = pagingData.collectAsLazyPagingItems()
    val hasProgress by remember { mutableStateOf(movies.itemCount) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TitleBar()
        MainAppBar(
            searchWIdgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = {
                viewModel.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                viewModel.updateSearchTextState(viewModel.getRandomSearchTitle())

                viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
            },
            onSearchClicked = {
                viewModel.getMovie(it)
            },
            onSearchTriggered = {
                viewModel.updateSearchTextState("")
                viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPEN)
            })


        Spacer(modifier = Modifier.height(10.dp))
        FilterMovies(
            filterState = filterState,
            onFilterClicked = {
                viewModel.updateFilterState(it)
                viewModel.getMovie(searchTextState, it)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid( columns = GridCells.Fixed(3)) {
            items(movies.itemCount) { index ->
                movies[index]?.let { movie ->
                    MovieItem(movie = movie)
                }
            }
            when (movies.loadState.append) {
                LoadState.Loading -> {
                    items(3) {
                        CircularProgressBar()
                    }
                }
                else -> {}
            }
        }

        when {
            hasProgress in Int.MIN_VALUE..0 && searchWidgetState == SearchWidgetState.CLOSED -> {
                CircularProgressBar()
            }
        }
    }
}
