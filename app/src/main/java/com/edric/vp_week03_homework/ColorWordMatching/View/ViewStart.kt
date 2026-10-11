package com.edric.vp_week03_homework.ColorWordMatching.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme

@Composable
internal fun viewStart(startGame: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(10f))
        Text(
            text = "Welcome\nto\nColor Word Matching",
            style = AppTheme.h2Style,
            modifier = Modifier.weight(4f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = startGame,
            colors = AppTheme.buttonStyle,
            modifier = Modifier.weight(1.5f)
        ) {
            AppTheme.buttonText("Start Game")
        }
        Spacer(modifier = Modifier.weight(10f))
    }
}
