package com.example.fimovie.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fimovie.R
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.model.dto.Search
import com.example.fimovie.presentation.MovieEvent
import com.example.fimovie.presentation.MovieViewModel
import com.example.fimovie.ui.appbar.MainAppBar
import com.example.fimovie.ui.appbar.TitleBar
import com.example.fimovie.ui.filter_options.FilterMovies
import com.example.fimovie.ui.navigation.Screen
import com.example.fimovie.ui.progressbar.CircularProgressBar
import com.example.fimovie.ui.theme.IconColor
import kotlinx.coroutines.launch

@Composable
fun MovieListScreen(viewModel: MovieViewModel, navController: NavController) {
    val state = viewModel.bookmarkMovies.collectAsState()
    val searchTextState by viewModel.searchTextState
    val searchWidgetState by viewModel.searchWidgetState
    val filterState by viewModel.filterState
    val pagingData = viewModel.getMovie(searchTextState)
    val movies: LazyPagingItems<Search> = pagingData.collectAsLazyPagingItems()
    val hasProgress by remember { mutableStateOf(movies.itemCount) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TitleBar(imageIcon = R.drawable.ic_bookmark, onBookMarkClicked = {
            navController.navigate(Screen.BookmarkScreen.route)
        })
        MainAppBar(searchWIdgetState = searchWidgetState,
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
        FilterMovies(filterState = filterState, onFilterClicked = {
            viewModel.updateFilterState(it)
            viewModel.getMovie(searchTextState, it)
        })
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(movies.itemCount) { index ->
                movies[index]?.let { movie ->
                    MovieItem(movie = movie,
                        onBookmarkClick = { viewModel.insertBookmarkItem(movie) },
                        onBookmarkUnSelect = { viewModel.deleteBookmarkItem(movie) },
                        isBookMarked = state.value.contains(movie)
                    )
                }
            }
            when (movies.loadState.append) {
                LoadState.Loading -> {
                    items(3) {
                        CircularProgressBar(false)
                    }
                }
                else -> {}
            }
        }

        when {
            hasProgress in Int.MIN_VALUE..0 && searchWidgetState == SearchWidgetState.CLOSED -> {
                CircularProgressBar(true)

            }
        }
    }
}
