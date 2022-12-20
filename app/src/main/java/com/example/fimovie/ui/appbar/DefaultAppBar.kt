package com.example.fimovie.ui.appbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fimovie.R
import com.example.fimovie.ui.theme.IconColor
import com.example.fimovie.ui.theme.SearchBarColor
import com.example.fimovie.ui.theme.SearchTitleColor

@Composable
fun DefaultAppBar(
    onSearchTriggered: () -> Unit,

    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = SearchBarColor)
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onSearchTriggered) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "null",
                tint = IconColor
            )
        }

        Text(
            text = "search movie or show",
            color = SearchTitleColor,
            modifier = Modifier.clickable(onClick = onSearchTriggered)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_microphone),
            contentDescription = "null",
            tint = IconColor,
            modifier = Modifier.size(26.dp)
        )
    }
}
