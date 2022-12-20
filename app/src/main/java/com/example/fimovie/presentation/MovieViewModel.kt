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
import com.example.fimovie.data.local.movies
import com.example.fimovie.domain.MoviePaging
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.FilterState
import com.example.fimovie.model.SearchWidgetState
import com.example.fimovie.model.dto.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import java.util.logging.Filter
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

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

}

data class MovieFilter(
    val isSelected : Boolean,
    val movieName : FilterState
)

sealed class UiEvent{
    object FilterSelected : UiEvent()
}