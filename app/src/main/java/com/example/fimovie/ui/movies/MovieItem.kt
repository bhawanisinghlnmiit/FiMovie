package com.example.fimovie.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fimovie.model.dto.Search
import com.example.fimovie.ui.theme.IconColor
import com.example.fimovie.ui.theme.LightBlack
import com.example.fimovie.ui.theme.SubtitleColor

@Composable
fun MovieItem(
    movie: Search,
    onBookmarkClick : (Search) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(148.dp)
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(LightBlack, Color.Transparent),
                            startY = size.height / 6,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                model = movie?.Poster,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = "bookmark",
                tint = IconColor,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp)
                    .padding(4.dp)
                    .clickable {
                        onBookmarkClick(movie)
                    }
                )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movie?.Title ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(
                text = if (movie?.Type == "movie") "Movie" else "Series",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = SubtitleColor
            )
            Spacer(modifier = Modifier.width(4.dp))
//            Text(
//                text = movie?.imdbID.toString(),
//                color = SubtitleColor,
//                maxLines = 1,
//            )
        }

    }
}
