package com.edric.vp_week03_homework.RockPaperScissors.Model

enum class Selection(
    val sign:String
) {
    ROCK("✊"),
    PAPER("✋"),
    SCISSOR("✌\uFE0F");

    val beats: Selection
        get() = when (this){
            ROCK -> SCISSOR
            PAPER -> ROCK
            SCISSOR -> PAPER
        }
}