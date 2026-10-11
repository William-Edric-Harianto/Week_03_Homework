package com.edric.vp_week03_homework.RockPaperScissors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.edric.vp_week03_homework.RockPaperScissors.Model.AppTheme
import com.edric.vp_week03_homework.RockPaperScissors.Model.AppState
import com.edric.vp_week03_homework.RockPaperScissors.Model.Result
import com.edric.vp_week03_homework.RockPaperScissors.Model.Selection
import com.edric.vp_week03_homework.RockPaperScissors.View.finished
import com.edric.vp_week03_homework.RockPaperScissors.View.initial
import com.edric.vp_week03_homework.RockPaperScissors.View.pick
import com.edric.vp_week03_homework.RockPaperScissors.View.reveal
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.time.Duration.Companion.milliseconds

val bestOf = intArrayOf(3, 5, 7).random()
val firstTo = (bestOf / 2) + 1

@Composable
fun rockPaperScissorsView() {
    var appState by rememberSaveable { mutableStateOf(AppState.INITIAL) }
    var humanSelection by rememberSaveable { mutableStateOf(Selection.ROCK) }
    var botSelection by rememberSaveable { mutableStateOf(Selection.ROCK) }
    var humanScore by rememberSaveable { mutableIntStateOf(0) }
    var botScore by rememberSaveable { mutableIntStateOf(0) }
    var bestScore by rememberSaveable { mutableIntStateOf(0) }
    val resetGame = {
        humanScore = 0
        botScore = 0
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.backgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = "\uD83E\uDDD2 $humanScore - $botScore \uD83E\uDD16",
                        style = AppTheme.h3Style,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Best of $bestOf",
                        style = AppTheme.h3Style,
                        modifier = Modifier.weight(1f)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(9f)
                ) {
                    when (appState) {
                        AppState.INITIAL -> initial { appState = AppState.PICK }

                        AppState.PICK -> {
                            pick(
                                onRockClick = {
                                    humanSelection = Selection.ROCK
                                    appState = AppState.REVEAL
                                },
                                onPaperClick = {
                                    humanSelection = Selection.PAPER
                                    appState = AppState.REVEAL
                                },
                                onScissorsClick = {
                                    humanSelection = Selection.SCISSOR
                                    appState = AppState.REVEAL
                                }
                            )
                        }

                        AppState.REVEAL -> {
                            LaunchedEffect(appState) {
                                botSelection = Selection.entries.random()
                                delay(700.milliseconds)
                                when (getResult(humanSelection, botSelection)) {
                                    Result.WIN -> humanScore++
                                    Result.LOSE -> botScore++
                                    Result.DRAW -> {}
                                }
                                if ((humanScore >= firstTo) || (botScore >= firstTo)) {
                                    appState = AppState.FINISHED
                                } else {
                                    appState = AppState.PICK
                                }
                            }
                            reveal(
                                humanSelection,
                                botSelection,
                                getResult(
                                    humanSelection,
                                    botSelection
                                ).text
                            )
                        }

                        AppState.FINISHED -> {
                            val win = humanScore > botScore
                            bestScore = max(bestScore, humanScore - botScore)
                            finished(
                                message = if (win) "You Win the Match!" else "You Lost the Match",
                                bestScore = bestScore,
                                restartButton = {
                                    resetGame()
                                    appState = AppState.PICK
                                },
                                exitButton = {
                                    resetGame()
                                    appState = AppState.INITIAL
                                }
                            )
                        }
                    }
                }

            }
            Spacer(Modifier.weight(1f))
        }
    }
}

private fun getResult(
    humanSelection: Selection,
    botSelection: Selection
): Result {
    if (humanSelection == botSelection) return Result.DRAW
    else if (humanSelection.beats == botSelection) return Result.WIN
    else return Result.LOSE
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun rockPaperScissorsPreview() {
    rockPaperScissorsView()
}
