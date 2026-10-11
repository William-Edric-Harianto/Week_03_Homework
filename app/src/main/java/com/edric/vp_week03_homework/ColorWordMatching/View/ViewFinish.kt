package com.edric.vp_week03_homework.ColorWordMatching.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme

@Composable
internal fun viewFinish(
    score: Int,
    bestScore: Int,
    restartGame: () -> Unit,
    exitGame: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(8f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Game Over!",
                style = AppTheme.h1Style,
                modifier = Modifier.weight(4f)
            )
        }
        Spacer(modifier = Modifier.weight(3f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(4f)
        ) {
            Text(
                text = "Your Score",//typo mas
                style = AppTheme.h2Style
            )
            Text(
                text = score.toString(),
                style = AppTheme.h2Style
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
        ) {
            Text(
                text = "Best Score",
                style = AppTheme.h3Style
            )
            Text(
                text = bestScore.toString(),
                style = AppTheme.h3Style
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = restartGame,
            colors = AppTheme.buttonStyle,
            modifier = Modifier.weight(2f)
        ) {
            AppTheme.buttonText("Restart Game")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = exitGame,
            colors = AppTheme.buttonStyle,
            modifier = Modifier.weight(2f)
        ) {
            AppTheme.buttonText("Exit")
        }
        Spacer(modifier = Modifier.weight(8f))
    }
}
