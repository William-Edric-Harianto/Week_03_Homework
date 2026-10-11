package com.edric.vp_week03_homework.ColorWordMatching.Model

import androidx.compose.ui.graphics.Color

internal enum class Answer(
    val ink: Color
)
{
    RED(Color.Red),
    GREEN(Color.Green),
    YELLOW(Color.Yellow),
    BLUE(Color.Blue),
    ORANGE(Color(0xFFFF9700)),
    PURPLE(Color(0xFF7F00FF)),
}