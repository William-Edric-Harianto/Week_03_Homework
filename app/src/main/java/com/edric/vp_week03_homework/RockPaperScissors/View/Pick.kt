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
fun pick(
    onRockClick: () -> Unit,
    onPaperClick: () -> Unit,
    onScissorsClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(2f))
        Text(
            text = "Pick your move!",
            style = AppTheme.h3Style,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "❔ VS ❔",
            style = AppTheme.h1Style,
            modifier = Modifier.weight(1.5f)
        )
        Spacer(Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.5f)
        ) {
            Spacer(Modifier.weight(1f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = onRockClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(16f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "✊Rock",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = onPaperClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(16f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "✋Paper",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = onScissorsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(16f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "✌\uFE0FScissor",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}