package com.edric.vp_week03_homework.ColorWordMatching.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme.maxMistakes
import com.edric.vp_week03_homework.ColorWordMatching.Model.Problem

@Composable
internal fun viewPlay(
    score: Int,
    mistakes: Int,
    secondsRemaining: Int,
    problem: Problem,
    buttonSettings: Problem.ButtonSettings
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
        ) {
            Text(
                text = "Mode: ${problem.gameMode}",
                style = AppTheme.h3Style,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "✅ $score   ❌ $mistakes/$maxMistakes",
                style = AppTheme.h3Style,
                modifier = Modifier.weight(1f)
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
                text = "$secondsRemaining s",
                style = AppTheme.h2Style,
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = problem.chosenAnswer.word.toString(),
                style = TextStyle(
                    fontSize = 54.sp,
                    color = problem.chosenAnswer.ink.ink,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.fillMaxWidth().weight(3f)
            )
        }
        Spacer(modifier = Modifier.weight(1.25f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = buttonSettings.leftOnClick,
                colors = AppTheme.buttonStyle,
                modifier = Modifier.weight(4f)
            ) {
                AppTheme.buttonText(buttonSettings.leftText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = buttonSettings.rightOnClick,
                colors = AppTheme.buttonStyle,
                modifier = Modifier.weight(4f)
            ) {
                AppTheme.buttonText(buttonSettings.rightText)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(3f))
    }
}
