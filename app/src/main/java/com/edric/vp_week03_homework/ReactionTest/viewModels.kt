package com.edric.vp_week03_homework.ReactionTest

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import kotlin.time.Duration.Companion.milliseconds

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
    fontSize = 14.sp,
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
            if(appState.withCard){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                ) {
                    Spacer(Modifier.weight(1f))
                    middleView(
                        appState,
                        imgRes = if (appState == AppState.RESULT) getRank(trials).imgRes else -1,
                        title = if (appState == AppState.RESULT) getRank(trials).message
                        else if (appState == AppState.SUCCESS) "Trial ${trials.size} Complete!"
                        else appState.title,
                        subtitle = if (appState == AppState.RESULT) "Average: ${
                            trials.average().toLong().milliseconds
                        }"
                        else if (appState == AppState.SUCCESS) "Time: ${trials.lastOrNull()?.milliseconds}"
                        else appState.subtitle,
                        normal = if (appState == AppState.SUCCESS) "Average: Continue to Trial ${trials.size + 1}" else appState.normal,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(3f)
                    )
                }
            }else {
                middleView(
                    appState,
                    imgRes = if (appState == AppState.RESULT) getRank(trials).imgRes else -1,
                    title = if (appState == AppState.RESULT) getRank(trials).message
                    else if (appState == AppState.SUCCESS) "Trial ${trials.size} Complete!"
                    else appState.title,
                    subtitle = if (appState == AppState.RESULT) "Average: ${
                        trials.average().toLong().milliseconds
                    }"
                    else if (appState == AppState.SUCCESS) "Time: ${trials.lastOrNull()}ms"
                    else appState.subtitle,
                    normal = if (appState == AppState.SUCCESS) "Average: Continue to Trial ${trials.size + 1}" else appState.normal,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            if (appState.withCard) Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                .weight(1f)
            ) {
                resultCard(
                    appState, trials,
                    modifier = Modifier
                        .padding(horizontal = 80.dp)
                        .weight(2f)
                        .fillMaxWidth()
                )
                Spacer(Modifier.weight(1f))
            }
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
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (appState.withCard) Spacer(Modifier.size(20.dp))
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
        )
    }
}

@Composable
private fun resultCard(appState: AppState, trials: MutableList<Long>, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(0.8f)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Trial Results",
                color = Color.Blue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
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
                    ) {
                        Text(
                            text = i.toString(),
                            color = if (i <= trials.size) Color.Green else Color.DarkGray,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(
                            text = if (i <= trials.size) trials[i - 1].toString() + "ms" else "-",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                }
            }
            if (appState.withAverage) {
                Text(
                    text = "Average Score",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                text = trials.average().toLong().milliseconds.toString(),
                    color = Color(0xFFFF9700),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
            Spacer(Modifier.padding(bottom = 20.dp))
        }
    }
}

private fun getRank(trials: MutableList<Long>): Rank {
    val avg = trials.average().toLong().milliseconds
    when {
        avg < Rank.A.cutOff.milliseconds -> return Rank.A
        avg < Rank.B.cutOff.milliseconds -> return Rank.B
        avg < Rank.C.cutOff.milliseconds -> return Rank.C
        else -> return Rank.D
    }
}