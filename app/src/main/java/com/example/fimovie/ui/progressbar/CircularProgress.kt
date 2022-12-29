package com.example.fimovie.ui.progressbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fimovie.ui.theme.IconColor
import com.example.fimovie.ui.theme.SubtitleColor

@Composable
fun CircularProgressBar(
    showText : Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = IconColor
        )
        if(showText){
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Beam me up Scottie!", color = IconColor
            )
        }
    }
}
