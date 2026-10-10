package com.edric.vp_week03_homework.ReactionTest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds
val testAmount = 3
@Composable
fun reactionTestView() {
    val trials = rememberSaveable(
        saver = listSaver(
            save = { n -> n.toList() },
            restore = { n -> n.toMutableStateList() }
        )
    ) {
        mutableStateListOf<Long>()
    }
    var appState by rememberSaveable { mutableStateOf(AppState.START) }
    var reactStartTime by rememberSaveable { mutableLongStateOf(-1L) }
    when (appState) {
        AppState.START -> viewModel(
            appState, trials,
            onClick = {
                trials.clear()
                appState = AppState.WAIT
            }
        )

        AppState.WAIT -> {
            viewModel(
                appState, trials,
                onClick = {
                    appState = AppState.FAIL
                }
            )
            LaunchedEffect(key1 = appState) {
                delay((500L..4500L).random().milliseconds)
                appState = AppState.GO
            }
        }

        AppState.GO -> {
            viewModel(
                appState, trials,
                onClick = {
                    val reactionTime = System.currentTimeMillis() - reactStartTime
                    trials.add(reactionTime)
                    if (trials.size < testAmount) {
                        appState = AppState.SUCCESS
                    } else {
                        appState = AppState.RESULT
                    }
                }
            )
            LaunchedEffect(key1 = appState) {
                reactStartTime = System.currentTimeMillis()
            }
        }

        AppState.FAIL

            -> viewModel(
            appState, trials,
            onClick = {
                appState = AppState.WAIT
            }
        )

        AppState.SUCCESS -> viewModel(
            appState, trials,
            onClick = {
                appState = AppState.WAIT
            }
        )

        AppState.RESULT -> {
            viewModel(
                appState, trials,
                onClick = {
                    appState = AppState.START
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun reactionTestPreview() {
    reactionTestView()
}
