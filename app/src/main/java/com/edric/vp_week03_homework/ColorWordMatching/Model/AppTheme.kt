package com.edric.vp_week03_homework.ColorWordMatching.Model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

internal object AppTheme {
    const val maxMistakes = 3
    val backgroundColor = Color(0xFFFAFAFA)
    val buttonBackgroundColor = Color(0xFFD4DCDF)
    val buttonForegroundColor = Color(0xFF3A4750)
    val defaultTextColor = Color(0xFF3A4750)
    val h1Style = TextStyle(
        fontSize = 42.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        color = defaultTextColor
    )
    val h2Style = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        color = defaultTextColor
    )
    val h3Style = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        color = defaultTextColor
    )

    val buttonStyle: ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = buttonBackgroundColor,
            contentColor = buttonForegroundColor
        )
    val buttonTextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    )
    @Composable
    fun buttonText(text: String){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = text,
                style = buttonTextStyle
            )
        }
    }
}