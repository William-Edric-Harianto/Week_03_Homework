package com.edric.vp_week03_homework.RockPaperScissors.View

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun initial(onStartButtonClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(3f))
        Text(
            text = "Rock • Paper • Scissors",
            style = AppTheme.h2Style,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.25f)
        ) {
            Spacer(Modifier.weight(1f))
            Button(
                colors = AppTheme.buttonStyle,
                onClick = onStartButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Start",
                    style = AppTheme.buttonTextStyle
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}