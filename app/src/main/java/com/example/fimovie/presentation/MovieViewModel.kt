package com.example.fimovie.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.data.local.movies
import com.example.fimovie.domain.MoviePaging
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.domain.use_case.MoviesUseCases
import com.example.fimovie.model.FilterState
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.model.dto.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.RowId
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val moviesUseCases: MoviesUseCases, private val movieRepository: MovieRepository) :
    ViewModel() {

    init {
        getBookmarkMovies()
    }
    private val _state : MutableState<List<Search>> = mutableStateOf(emptyList())
    val state : State<List<Search>> = _state

    private var recentlyDeletedMovie : Search? = null

    fun onEvent(event: MovieEvent){
        when(event) {
            is MovieEvent.InsertMovie -> {
                viewModelScope.launch {
                    moviesUseCases.insertMovieInBookmarkUseCase(movie = event.movie)
                }
            }
            is MovieEvent.RestoreMovie -> {
                viewModelScope.launch {
                    moviesUseCases
                        .insertMovieInBookmarkUseCase(
                            recentlyDeletedMovie ?: return@launch
                        )
                    recentlyDeletedMovie = null
                }

            }
            is MovieEvent.DeleteMovie -> {
                viewModelScope.launch {
                    moviesUseCases.deleteBookmarkMovieUseCase(movie = event.movie)
                    recentlyDeletedMovie = event.movie
                }
            }
            is MovieEvent.GetAllBookmarkMovies -> {
                viewModelScope.launch {
                    moviesUseCases.getBookmarkMoviesUseCase()
                }
            }
//            is MovieEvent.GetMovie -> {
//                viewModelScope.launch {
//                    moviesUseCases.getMovieByImdb(event.imdbId)
//                }
//            }
        }
    }

    fun getMovie(s: String = _searchTextState.value, type : FilterState = _filterState.value): Flow<PagingData<Search>> =
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(s, type, movieRepository)
        }
            .flow
            .debounce(300)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.Default)

    fun getRandomSearchTitle() = movies.random()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(getRandomSearchTitle())
    val searchTextState: State<String> = _searchTextState

    private val _filterState : MutableState<FilterState> = mutableStateOf(FilterState.HOME)
    val filterState : State<FilterState> = _filterState

    fun updateFilterState(newValue: FilterState){
        _filterState.value = newValue
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun insertBookmarkItem(movie : Search){
        viewModelScope.launch {
            movieRepository.insertMovie(movie)
        }
    }

    fun deleteBookmarkItem(movie : Search){
        viewModelScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }

    private val _bookmarkMovies : MutableStateFlow<List<Search>> = MutableStateFlow(emptyList())
    val bookmarkMovies : StateFlow<List<Search>> = _bookmarkMovies

    private fun getBookmarkMovies(){
        viewModelScope.launch {
            movieRepository.getBookmarkMovies().collect{
                _bookmarkMovies.value = it
            }
        }
    }

//    private val _bookmarkMovie : MutableState<List<Search>> = mutableStateOf(emptyList())
//    val bookmarkMovie : State<List<Search>> = _bookmarkMovie
//
//    fun getMovieByImdbId(imdbId: String) {
//        viewModelScope.launch {
//            movieRepository.getMovie(imdbId).collect{
//                _bookmarkMovie.value = it
//            }
//        }
//    }

}
