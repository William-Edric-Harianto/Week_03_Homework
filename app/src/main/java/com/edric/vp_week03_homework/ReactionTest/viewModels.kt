package com.edric.vp_week03_homework.ReactionTest

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val titleStyle = TextStyle(
    fontSize = 36.sp,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    color = Color.White
)
private val subtitleStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Center,
    color = Color.White
)
private val normalStyle = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Center,
    color = Color.White
)
private val defaultIcon = Icons.Default.Block

@Composable
fun viewModel(appState: AppState, trials: MutableList<Long>, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (appState == AppState.RESULT) getRank(trials).backgroundColor else appState.backgroundColor)
            .clickable { onClick() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            middleView(
                appState,
                imgRes = if (appState == AppState.RESULT) getRank(trials).imgRes else -1,
                title = if (appState == AppState.RESULT) getRank(trials).message
                else if (appState == AppState.SUCCESS) "Trial ${trials.size} Complete!"
                else appState.title,
                subtitle = if (appState == AppState.RESULT) "Average: ${
                    trials.average().toLong()
                }ms"
                else if (appState == AppState.SUCCESS) "Time: ${trials.lastOrNull()}ms"
                else appState.subtitle,
                normal = if (appState == AppState.SUCCESS) "Average: {Continue to Trial${trials.size + 1}}" else appState.normal,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
            )
            if (appState.withCard) resultCard(
                appState, trials,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
                    .fillMaxSize()
            )
        }

    }
}

@Composable
private fun middleView(
    appState: AppState,
    @DrawableRes imgRes: Int,
    title: String,
    subtitle: String,
    normal: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = titleStyle,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (appState.usesIcon) {
                Icon(
                    imageVector = appState.icon ?: defaultIcon,
                    modifier = Modifier.size(192.dp),
                    tint = Color.White,
                    contentDescription = null
                )
            } else {
                Image(
                    painter = painterResource(id = imgRes),
                    contentDescription = null,
                    modifier = Modifier.size(192.dp)
                )
            }
        }
        Text(
            text = subtitle,
            style = subtitleStyle,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Text(
            text = normal,
            style = normalStyle,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun resultCard(appState: AppState, trials: MutableList<Long>, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Trial Results",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 1..testAmount) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = i.toString(),
                            color = if (i <= trials.size) Color.Green else Color.DarkGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = if (i <= trials.size) trials[i - 1].toString() + "ms" else "-",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            if (appState.withAverage) {
                Text(
                    text = "Average Score",
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = trials.average().toString() + "ms",
                    color = Color(0xFFFF9700),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

private fun getRank(trials: MutableList<Long>): Rank {
    val avg = trials.average().toLong()
    when {
        avg < Rank.A.cutOff -> return Rank.A
        avg < Rank.B.cutOff -> return Rank.B
        avg < Rank.C.cutOff -> return Rank.C
        else -> return Rank.D
    }
}