package com.example.fimovie.ui.filter_options

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fimovie.model.FilterState
import com.example.fimovie.presentation.MovieFilter
import com.example.fimovie.ui.theme.SearchBarColor

@Composable
fun FilterMovies(
    filterState : FilterState,
    onFilterClicked : (FilterState) -> Unit
){

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        filterList.forEach { s  ->
            FilterItem(
                filterValue = s,
                selectedFilterState = filterState,
                onFilterClicked = onFilterClicked
            )
        }
    }
}

val filterList  = listOf(
    FilterState.HOME,
    FilterState.MOVIES,
    FilterState.SERIES,
    FilterState.EPISODES
)