package com.edric.vp_week03_homework.ColorWordMatching

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppState
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme
import com.edric.vp_week03_homework.ColorWordMatching.Model.GameMode
import com.edric.vp_week03_homework.ColorWordMatching.Model.Problem
import com.edric.vp_week03_homework.ColorWordMatching.View.viewCountdown
import com.edric.vp_week03_homework.ColorWordMatching.View.viewFinish
import com.edric.vp_week03_homework.ColorWordMatching.View.viewPlay
import com.edric.vp_week03_homework.ColorWordMatching.View.viewStart
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun colorWordMatchingView() {
    val context = LocalContext.current
    var bestScore by rememberSaveable { mutableIntStateOf(0) }
    var appState by rememberSaveable { mutableStateOf(AppState.START) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var mistakes by rememberSaveable { mutableIntStateOf(0) }
    var secondsRemaining by rememberSaveable { mutableIntStateOf(0) }
    var mySaveableString by rememberSaveable { mutableStateOf("") }
    var problem by rememberSaveable(
        listSaver<Problem, Any>(
            save = {
                listOf(
                    it.gameMode,
                    it.chosenAnswer,
                    it.leftText,
                    it.rightText,
                    it.left,
                    it.right
                )
            },
            restore = { Problem(it) }
        )
    )
//    val gameMode: GameMode = GameMode.entries.random()
//    val chosenAnswer = getAnswer()
//    var leftText =  ""
//    var rightText =  ""
//    var left = {}
//    var right = {}
    {
        mutableStateOf(
            Problem(
                correct = {
                    score++
                    secondsRemaining = 5
                    appState = AppState.PLAY
                },
                wrong = {
                    mistakes++
                    if (mistakes >= 3) {
                        appState = AppState.FINISH
                    } else {
                        secondsRemaining = 5
                        appState = AppState.PLAY
                    }
                }
            )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.backgroundColor)
    ) {
        when (appState) {
            AppState.START -> viewStart(startGame = {
                score = 0
                mistakes = 0
                appState = AppState.COUNTDOWN
            }
            )

            AppState.COUNTDOWN -> {
                LaunchedEffect(appState) {
                    var secondsRemaining = 3
                    while (true) {
                        when {
                            secondsRemaining < 0 -> {
                                secondsRemaining = 5
                                appState = AppState.PLAY
                            }

                            secondsRemaining < 1 -> {
                                mySaveableString = "Start!"
                                delay(0.7.seconds)
                            }

                            else -> {
                                mySaveableString = secondsRemaining.toString()
                                delay(1.seconds)
                            }
                        }
                        secondsRemaining--
                    }
                }
                viewCountdown(mySaveableString)
            }

            AppState.PLAY -> {
                LaunchedEffect(appState) {
                    while (true) {
                        delay(1.seconds)
                        secondsRemaining--
                        if (secondsRemaining < 1) mistakes++
                        if (mistakes >= 3) {
                            appState = AppState.FINISH
                            secondsRemaining = 5
                            appState = AppState.PLAY
                        }
                    }
                }
                viewPlay(
                    score, mistakes, secondsRemaining, problem
                )
                )
            }

            AppState.FINISH -> viewFinish(
                score, bestScore,
                restartGame = { appState = AppState.START },
                exitGame = { (context as? Activity)?.finish() }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun colorWordMatchingPreview() {
    colorWordMatchingView()
}