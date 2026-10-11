package com.edric.vp_week03_homework.ReactionTest

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.edric.vp_week03_homework.R

internal enum class Rank(
    val cutOff: Long,
    val message: String,
    val backgroundColor: Color,
    @DrawableRes val imgRes: Int
) {
    A(
        cutOff = 180L,
        message = "DANG YOU ARE SO FAST BRO!",
        backgroundColor = Color(0xFF00E676),
        imgRes = R.drawable.kokonya_rispek
    ),
    B(
        cutOff = 280L,
        message = "YOUR REFLEX IS GOOD",
        backgroundColor = Color(0xFF2196F3),
        imgRes = R.drawable.kokonya_thumbsup
    ),
    C(
        cutOff = 450L,
        message = "MEH LIKE OTHER PERSON",
        backgroundColor = Color(0xFFFF9700),
        imgRes = R.drawable.kokonya_hepi
    ),
    D(
        cutOff = Long.MAX_VALUE,
        message = "YOU LIKE A SNAIL BRO",
        backgroundColor = Color(0xFFFE5722),
        imgRes = R.drawable.kokonya_thumbsdown
    )
}