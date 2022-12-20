package com.example.fimovie.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fimovie.R

val giroyFont = FontFamily(
    Font(R.font.gilroy_regular),
    Font(R.font.gilroy_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(body1 = TextStyle(fontFamily = giroyFont, fontWeight = FontWeight.Normal, fontSize = 16.sp)/* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */)
