package com.edric.vp_week03_homework.ColorWordMatching.Model

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

internal  class Problem(correct: () -> Unit, wrong: () -> Unit) {

    val gameMode: GameMode = GameMode.entries.random()
    val chosenAnswer = getAnswer()
    var leftText =  ""
    var rightText =  ""
    var left = {}
    var right = {}

    data class ChosenAnswer(
        val ink: Answer,
        val word: Answer,
        val leftMode: GameMode
    )

    init {
        if (Random.nextBoolean()) { // random flagnya ini ko
            leftText =  chosenAnswer.word.toString()
            rightText =  chosenAnswer.ink.toString()
            left = correct
            right = wrong
        }else {
            leftText =  chosenAnswer.ink.toString()
            rightText =  chosenAnswer.word.toString()
            left = wrong
            right = correct
        }
    }

    private fun getAnswer(): ChosenAnswer {
        val ink = Answer
            .entries
            .random()
        val word = Answer
            .entries
            .filterNot { it == ink }
            .random()
        return ChosenAnswer(
            ink = ink,
            word = word,
            leftMode = GameMode.entries.random()
        )
    }

}