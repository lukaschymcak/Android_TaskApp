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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.navigation.Screens.TripScreen
import com.example.navigation.Screens.WelcomeScreen
import kotlinx.coroutines.launch

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
    var tripList by remember { mutableStateOf<List<TripModel>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            dataStoreManager.getTrips().collect { trips ->
                tripList = trips
            }
        }
    }
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
                dataStoreManager = dataStoreManager,
                navController = navController
            )
        }

        composable(
            route = "${Screen.TripScreen.route}/{tripName}",
            arguments = listOf(navArgument("tripName") { type = NavType.StringType })
        ) { backStackEntry ->
            val tripName = backStackEntry.arguments?.getString("tripName") ?: ""
            val trip = tripList.firstOrNull { it.name == tripName }

            TripScreen(
                onGoBack = { navController.popBackStack() },
                tripModel = trip ?: TripModel("", "", ""),
                dataStoreManager = dataStoreManager
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