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
    problem: Problem
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(5f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
        ) {
            Text(
                text = "Mode: ${problem.gameMode}",
                style = AppTheme.h3Style,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "✅ $score ❌ $mistakes/$maxMistakes",
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
                .weight(3f)
        ) {
            Text(
                text = "$secondsRemaining s",
                style = AppTheme.h2Style,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = problem.chosenAnswer.word.toString(),
                style = TextStyle(
                    fontSize = 36.sp,
                    color = problem.chosenAnswer.ink.ink,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = problem.left,
                colors = AppTheme.buttonStyle,
                modifier = Modifier.weight(1f)
            ) {
                AppTheme.buttonText(problem.leftText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = problem.right,
                colors = AppTheme.buttonStyle,
                modifier = Modifier.weight(1f)
            ) {
                AppTheme.buttonText(problem.rightText)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(5f))
    }
}
