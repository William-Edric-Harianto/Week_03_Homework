package com.edric.vp_week03_homework.ColorWordMatching.Model


import androidx.compose.runtime.saveable.listSaver
import kotlin.String
import kotlin.random.Random
internal data class Problem(
    val gameMode: GameMode,
    val chosenAnswer: ChosenAnswer,
    val isLeftCorrect: Boolean,
) {



    internal data class ChosenAnswer(
        val ink: Answer,
        val word: Answer
    )

    internal data class ButtonSettings(
        val leftText: String,
        val leftOnClick: () -> Unit,
        val rightText: String,
        val rightOnClick: () -> Unit,
    )

    companion object{

        val problemSaver = listSaver<Problem, Any>(
            save = {
                listOf(
                    it.gameMode,
                    it.chosenAnswer.ink,
                    it.chosenAnswer.word,
                    it.isLeftCorrect
                )
            },
            restore = { saved ->
                Problem(
                    gameMode = GameMode.valueOf(saved[0] as String),
                    chosenAnswer = ChosenAnswer(
                        ink = Answer.valueOf(saved[1] as String),
                        word = Answer.valueOf(saved[2] as String)
                    ),
                    isLeftCorrect = saved[4] as Boolean
                )
            }
        )


        internal fun makeNew(): Problem{
            val gameMode = GameMode.entries.random()
            val chosenAnswer = getAnswer()
            val isLeftCorrect = Random.nextBoolean()
            return Problem(gameMode,chosenAnswer,isLeftCorrect)
        }

        private fun getAnswer(): ChosenAnswer {
            val ink = Answer
                .entries
                .random()
            val word = Answer
                .entries
                .filterNot { it == ink }
                .random()
            return ChosenAnswer(ink,word)
        }
    }

    internal fun getButtonSettings(correct: () -> Unit,wrong: () -> Unit): ButtonSettings{

        val correctText: String
        val wrongText: String
        val leftText: String
        val leftOnClick: () -> Unit
        val rightText: String
        val rightOnClick:  () -> Unit

        if (gameMode == GameMode.TEXT){
            correctText = chosenAnswer.word.toString()
            wrongText = chosenAnswer.ink.toString()
        }else{
            correctText = chosenAnswer.ink.toString()
            wrongText = chosenAnswer.word.toString()
        }

        if (isLeftCorrect){
            leftText = correctText
            leftOnClick = correct
            rightText = wrongText
            rightOnClick = wrong
        }else{
            leftText = wrongText
            leftOnClick = wrong
            rightText = correctText
            rightOnClick = correct
        }

        return ButtonSettings(leftText,leftOnClick,rightText,rightOnClick)
    }

}