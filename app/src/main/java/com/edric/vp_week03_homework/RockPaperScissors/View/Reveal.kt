package com.edric.vp_week03_homework.RockPaperScissors.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edric.vp_week03_homework.RockPaperScissors.Model.AppTheme
import com.edric.vp_week03_homework.RockPaperScissors.Model.Selection

@Composable
fun reveal(
    humanSelection: Selection,
    botSelection: Selection,
    result: String
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(2f))
        Text(
            text = "${humanSelection.sign} VS ${botSelection.sign}",
            style = AppTheme.h1Style,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = result,
            style = AppTheme.h2Style,
            modifier = Modifier.weight(1f)
        )
    }
}