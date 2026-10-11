package com.edric.vp_week03_homework.ColorWordMatching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppState
import com.edric.vp_week03_homework.ColorWordMatching.Model.AppTheme
import com.edric.vp_week03_homework.ColorWordMatching.Model.Problem
import com.edric.vp_week03_homework.ColorWordMatching.View.viewCountdown
import com.edric.vp_week03_homework.ColorWordMatching.View.viewFinish
import com.edric.vp_week03_homework.ColorWordMatching.View.viewPlay
import com.edric.vp_week03_homework.ColorWordMatching.View.viewStart
import kotlinx.coroutines.delay
import kotlin.math.max
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
    var problem by rememberSaveable(stateSaver = Problem.problemSaver) {
        mutableStateOf(Problem.makeNew())
    }
    val endPlay = {
        bestScore = max(bestScore,score)
        appState = AppState.FINISH
    }
    val resetProblem = {
        secondsRemaining = 5
        problem = Problem.makeNew()
        appState = AppState.PLAY
    }
    val correct = {
        score++
        resetProblem()
    }
    val wrong = {
        mistakes++
        if (mistakes >= 3) endPlay() else resetProblem()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.backgroundColor)
    ) {
        when (appState) {
            AppState.START -> viewStart({appState = AppState.COUNTDOWN})

            AppState.COUNTDOWN -> {
                LaunchedEffect(appState) {
                    score = 0
                    mistakes = 0
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
                    secondsRemaining = 5
                    while (true) {
                        delay(1.seconds)
                        secondsRemaining--
                        if (secondsRemaining < 1) {
                            mistakes++
                            resetProblem()
                        }
                        if (mistakes >= 3) endPlay()
                    }
                }
                viewPlay(
                    score,
                    mistakes,
                    secondsRemaining,
                    problem,
                    problem.getButtonSettings(correct,wrong)
                )
            }

            AppState.FINISH -> viewFinish(
                score, bestScore,
                restartGame = { appState = AppState.COUNTDOWN },
                exitGame = { appState = AppState.START }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun colorWordMatchingPreview() {
    colorWordMatchingView()
}