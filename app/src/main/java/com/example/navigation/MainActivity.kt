package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.example.navigation.Screens.HomeScreen
import com.example.navigation.Screens.PackingScreen
import com.example.navigation.Screens.TripAddScreen
import com.example.navigation.Screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val startDestination = if (HomeScreenState.getWasShown()) {
                        Screen.HomeScreen
                    } else {
                        Screen.WelcomeScreen
                    }
                    Navigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier:Modifier = Modifier){

    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.PackingScreen,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } },
    ){
        composable<Screen.WelcomeScreen> {
            WelcomeScreen(
                onGoToNextScreen = {
                    HomeScreenState.setWasShown(true)
                    navController.navigate(Screen.HomeScreen)
                }
            )
        }
        composable<Screen.HomeScreen> { backStackEntry ->
            val homeScreen: Screen.HomeScreen = backStackEntry.toRoute()

            HomeScreen(
                onGoToNextScreen = {
                    navController.navigate(Screen.PackingScreen)
                }
            )
        }
        composable<Screen.PackingScreen> {
            PackingScreen(
                onGoToNextScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.TripAddScreen)
                },
                onGoBack = {
                    navController.popBackStack()
                }
            )

        }
        composable<Screen.TripAddScreen> {
            TripAddScreen(
                onGoBack = {
                    navController.popBackStack()
                }
            )

        }

        }
    }

