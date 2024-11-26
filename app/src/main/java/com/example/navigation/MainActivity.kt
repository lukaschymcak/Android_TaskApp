package com.example.navigation

import HomeScreen
import PackingScreen
import TripModel
import android.content.Context
import android.content.SharedPreferences
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
import com.example.navigation.Screens.TripScreen
import com.example.navigation.Screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStoreManager = DataStoreManager(applicationContext)

        setContent {
            NavigationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val startDestination = if (PreferencesHelper.isWelcomeScreenShown(this)) {
                        Screen.HomeScreen.route
                    } else {
                        Screen.WelcomeScreen.route
                    }
                    Navigation(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = startDestination,
                        dataStoreManager = dataStoreManager
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: String,
    dataStoreManager: DataStoreManager
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(
                onGoToNextScreen = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.WelcomeScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                onGoToNextScreen = {
                    navController.navigate(Screen.PackingScreen.route)
                },
                //dataStoreManager = dataStoreManager
            )
        }

        composable(Screen.PackingScreen.route) {
            PackingScreen(
                onGoToNextScreen = {
                    navController.navigate(Screen.TripScreen.route)
                },
                onGoBack = {
                    navController.popBackStack()
                },
                dataStoreManager = dataStoreManager
            )
        }

        composable(Screen.TripScreen.route) {
            TripScreen(
                onGoBack = {
                    navController.popBackStack()
                },
                dataStoreManager = dataStoreManager,
                tripModel = TripModel( "Trip 1", "2022-10-10", "2022-10-20")

                )
        }
    }
}


object PreferencesHelper {
    private const val PREFS_NAME = "my_prefs"
    private const val KEY_NAME = "name"
    private const val KEY_WELCOME_SCREEN_SHOWN = "welcome_screen_shown"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setName(context: Context, newName: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_NAME, newName)
        editor.apply()
    }

    fun getName(context: Context): String? {
        return getPreferences(context).getString(KEY_NAME, null)
    }

    fun setWelcomeScreenShown(context: Context, shown: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(KEY_WELCOME_SCREEN_SHOWN, shown)
        editor.apply()
    }

    fun isWelcomeScreenShown(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_WELCOME_SCREEN_SHOWN, false)
    }
}