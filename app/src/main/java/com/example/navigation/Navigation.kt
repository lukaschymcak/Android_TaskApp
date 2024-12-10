package com.example.navigation

import HomeScreen
import PackingScreen
import com.example.navigation.models.packing.TripModel
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigation.Screens.RecipeScreen
import com.example.navigation.Screens.SettingsScreen
import com.example.navigation.Screens.ShoppingScreen
import com.example.navigation.Screens.TripScreen
import com.example.navigation.Screens.WateringScreen
import com.example.navigation.Screens.WelcomeScreen
import kotlinx.coroutines.launch


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
        popExitTransition = { slideOutHorizontally { it } },

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
                dataStoreManager = dataStoreManager,
                onGoToSettings = {
                    navController.navigate(Screen.SettingsScreen.route)
                },
                onGoToWatering = {
                    navController.navigate(Screen.WateringScreen.route)
                },
                onGoToShopping = {
                    navController.navigate(Screen.ShoppingScreen.route)
                },
                onGoToRecipe = {
                    navController.navigate(Screen.RecipeScreen.route)
                }

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
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                onGoBack = { navController.popBackStack() }
            )
        }
        composable(Screen.WateringScreen.route) {
            WateringScreen(
                onGoBack = { navController.popBackStack() }
            )
        }
        composable(Screen.ShoppingScreen.route) {
            ShoppingScreen(
                onGoBack = { navController.popBackStack() }
            )
        }
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(
                onGoBack = { navController.popBackStack() }
            )
        }


    }
}
