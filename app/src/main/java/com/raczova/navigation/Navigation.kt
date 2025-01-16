package com.raczova.navigation

import HomeScreen
import com.raczova.navigation.Screens.packing.PackingScreen
import com.raczova.navigation.models.packing.TripModel
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raczova.navigation.Screens.watering.PlantAddScreen
import com.raczova.navigation.Screens.RecipeScreen
import com.raczova.navigation.Screens.SettingsScreen
import com.raczova.navigation.Screens.ShoppingScreen
import com.raczova.navigation.Screens.packing.TripScreen
import com.raczova.navigation.Screens.watering.WateringScreen
import com.raczova.navigation.Screens.WelcomeScreen
import com.raczova.navigation.Screens.watering.PlantConfigurationScreen
import com.raczova.navigation.Screens.onboardingViewPacking.OnboardingScreen
import com.raczova.navigation.Screens.onboardingViewWatering.WateringOnboardingScreen
import com.raczova.navigation.states.HomeScreenState
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
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val isOnboardingCompleted = HomeScreenState.isOnboardingPackingShown(context)

            if (isOnboardingCompleted) {
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
            } else {
                OnboardingScreen {
                    scope.launch {
                        HomeScreenState.setOnboardingPackingShown(context, true)
                        navController.navigate(Screen.PackingScreen.route) {
                            popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                        }
                    }
                }
            }
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
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val isWateringOnboardingCompleted = HomeScreenState.isOnboardingWateringShown(context)

            if (isWateringOnboardingCompleted) {
                WateringScreen(
                    onGoBack = { navController.navigate(Screen.HomeScreen.route) },
                    onGoToAddPlant = { navController.navigate(Screen.PlantAddScreen.route) },
                    onGoToPlantConfiguration = { navController.navigate(Screen.PlantConfigurationScreen.route) },
                    dataStoreManager = dataStoreManager
                )
            } else {
                WateringOnboardingScreen {
                    scope.launch {
                        HomeScreenState.setOnboardingWateringShown(context, true)
                        navController.navigate(Screen.WateringScreen.route) {
                            popUpTo(Screen.WateringOnboardingScreen.route) { inclusive = true }
                        }
                    }
                }
            }
        }

        composable(Screen.PlantAddScreen.route) {
            PlantAddScreen(
                onGoToWatering = { navController.navigate(Screen.WateringScreen.route) },
                dataStoreManager = dataStoreManager,
            )
        }
        composable(Screen.PlantConfigurationScreen.route) {
            PlantConfigurationScreen(
                onGoBack = { navController.popBackStack() },
                dataStoreManager = dataStoreManager

            )

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
}

