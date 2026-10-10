package com.edric.vp_week03_homework.ReactionTest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppState(
    val backgroundColor: Color = Color.White,
    val title: String="",
    val icon: ImageVector?,
    val subtitle: String="",
    val normal: String="",
    val usesIcon: Boolean,
    val withCard: Boolean,
    val withAverage: Boolean,
) {
    START(
        backgroundColor = Color(0xFF72CEDD),
        title = "Reaction",
        icon = Icons.Filled.Bolt,
        subtitle = "Test",
        normal = "Click to Start",
        usesIcon = true,
        withCard = false,
        withAverage = false
    ),
    WAIT(
        backgroundColor = Color(0xFFDBDBDB),
        title = "Get Ready",
        icon = Icons.Filled.Warning,
        subtitle = "Wait for green light...",
        normal = "DON'T CLICK YET",
        usesIcon = true,
        withCard = false,
        withAverage = false
    ),
    GO(
        backgroundColor = Color(0xFF4CB050),
        title = "GO!",
        icon = Icons.AutoMirrored.Filled.DirectionsRun,
        subtitle = "CLICK NOW!",
        normal = "TAP AS FAST AS YOU CAN!",
        usesIcon = true,
        withCard = false,
        withAverage = false
    ),
    FAIL(
        backgroundColor = Color(0xFFFE4445),
        title = "FAIL!",
        icon = Icons.Filled.ThumbDown,
        subtitle = "You clicked too early. TRY TO READ THE RULE BRO",
        normal = "TRY AGAIN",
        usesIcon = true,
        withCard = true,
        withAverage = false
    ),
    SUCCESS(
        backgroundColor = Color(0xFF4CB050),
        icon = Icons.Filled.CheckCircle,
        usesIcon = true,
        withCard = true,
        withAverage = false
    ),
    RESULT(
        icon = null,
        normal = "Click to Start New Test",
        usesIcon = false,
        withCard = true,
        withAverage = true
    )
}