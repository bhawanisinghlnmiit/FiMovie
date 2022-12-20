package com.example.fimovie.ui.filter_options

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fimovie.model.FilterState
import com.example.fimovie.presentation.MovieFilter
import com.example.fimovie.ui.theme.BackgroundColor
import com.example.fimovie.ui.theme.SearchBarColor
import com.example.fimovie.ui.theme.SelectedFilterColor
import com.example.fimovie.ui.theme.SubtitleColor
import java.util.Locale
import java.util.logging.Filter


@Composable
fun FilterItem(
    filterValue : FilterState,
    selectedFilterState: FilterState,
    onFilterClicked : (FilterState) -> Unit
){


    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onFilterClicked(filterValue)
            }
            .background(if(filterValue == selectedFilterState) SelectedFilterColor else SearchBarColor)
            .height(44.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)

    ){
        Text(
            text = filterValue.toString().lowercase().replaceFirstChar { it.uppercase() },
            color = if(filterValue == selectedFilterState) Color.White else SubtitleColor,
        )
    }
    
}