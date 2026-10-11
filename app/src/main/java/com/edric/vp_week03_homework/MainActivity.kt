package com.edric.vp_week03_homework

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.edric.vp_week03_homework.ClickerGame.clickerGameView
import com.edric.vp_week03_homework.ReactionTest.reactionTestView
import com.edric.vp_week03_homework.ui.theme.VP_Week03_HomeworkTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week03_HomeworkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    clickerGameView()
                }
            }
        }
    }
}