package com.edric.vp_week03_homework.ColorWordMatching.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme

@Composable
internal fun viewCountdown(text: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = text,
            style = AppTheme.h2Style,
            modifier = Modifier.fillMaxWidth()
        )

    }
}
