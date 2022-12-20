package com.example.fimovie.ui.appbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.fimovie.model.SearchWidgetState

@Composable
fun MainAppBar(
    searchWIdgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWIdgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(onSearchTriggered)
        }
        SearchWidgetState.OPEN -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,

                )
        }
    }
}
