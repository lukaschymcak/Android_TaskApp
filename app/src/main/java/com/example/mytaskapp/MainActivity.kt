package com.example.mytaskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytaskapp.Screens.HomeScreen
import com.example.mytaskapp.Screens.PackingScreen
import com.example.mytaskapp.Screens.TripScreen
import com.example.mytaskapp.ui.theme.MyTaskAppTheme


var welcomeScreenShown = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaskAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Navigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


@Composable
fun Navigation(modifier:Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.PackingScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(
                onGoToNextScreen = {
                    navController.navigate(Screen.PackingScreen)
                }
            )
        }
        composable<Screen.PackingScreen> {
            PackingScreen(
                id = 1,
                onGoToNextScreen = {
                    navController.navigate(Screen.TripScreen)
                }
            )

        }
        composable<Screen.TripScreen> {
            TripScreen(
                onGoBack = {
                    navController.popBackStack()
                }
            )

        }

    }
}}
