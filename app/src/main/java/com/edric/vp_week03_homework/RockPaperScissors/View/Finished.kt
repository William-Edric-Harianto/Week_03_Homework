package com.edric.vp_week03_homework.RockPaperScissors.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edric.vp_week03_homework.RockPaperScissors.Model.AppTheme

@Composable
fun finished(
    message: String,
    bestScore: Int,
    restartButton: () -> Unit,
    exitButton: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
        ) {
            Text(
                text = message,
                style = AppTheme.h2Style,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Best Score: $bestScore",
                style = AppTheme.h3Style,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Spacer(Modifier.weight(1f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = restartButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Restart",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(5f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = exitButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Exit",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}