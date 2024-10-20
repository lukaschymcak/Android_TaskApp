package com.example.mytaskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mytaskapp.ui.theme.MyTaskAppTheme

var welcomeScreenShown = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaskAppTheme {
//                if (!welcomeScreenShown) {
//                    welcomeScreenShown = true
//                    WelcomeScreen()
//                } else {
                    HomeScreen()
                }
            }
        }
    }
