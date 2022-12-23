package com.example.fimovie.ui.appbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fimovie.R
import com.example.fimovie.ui.theme.IconColor
import java.time.format.TextStyle

@OptIn(ExperimentalTextApi::class)
@Preview
@Composable
fun TitleBar() {
    val rainbowColorsBrush = remember {
        listOf(
            Color(0xFF9575CD),
            Color(0xFFBA68C8),
            Color(0xFFE57373),
            Color(0xFFFFB74D),
            Color(0xFFFFF176),
            Color(0xFFAED581),
            Color(0xFF4DD0E1),
            Color(0xFF9575CD)
        )
    }
    val borderWidth = 2.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                  .fillMaxSize(),
            text = "FiMovies",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            style = androidx.compose.ui.text.TextStyle(
                brush = Brush.horizontalGradient(
                    colors = rainbowColorsBrush
                )
            )
        )
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_bookmark),
            contentDescription = "",
            tint = IconColor
        )
    }
}
