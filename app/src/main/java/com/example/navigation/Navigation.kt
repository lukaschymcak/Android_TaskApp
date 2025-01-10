package com.example.navigation

import HomeScreen
import android.net.Uri
import android.widget.Toast
import com.example.navigation.Screens.packing.PackingScreen
import com.example.navigation.models.packing.TripModel
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigation.Screens.watering.PlantAddScreen
import com.example.navigation.Screens.RecipeScreen
import com.example.navigation.Screens.SettingsScreen
import com.example.navigation.Screens.ShoppingScreen
import com.example.navigation.Screens.packing.TripScreen
import com.example.navigation.Screens.watering.WateringScreen
import com.example.navigation.Screens.WelcomeScreen
import com.example.navigation.Screens.watering.PlantConfigurationScreen
import com.example.navigation.models.watering.PlantModel
import com.example.navigation.onboardingView.OnboardingScreen
import com.example.navigation.states.HomeScreenState
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun showOnboardingScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.background(Color.Red)){
        OnboardingScreen {
            Toast.makeText(context, "Onboarding finished", Toast.LENGTH_SHORT).show()
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
    val addedPlants = remember { mutableStateOf<List<PlantModel>>(emptyList()) }


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

//        composable(Screen.PackingScreen.route) {
//            PackingScreen(
//                onGoToNextScreen = {
//                    navController.navigate(Screen.TripScreen.route)
//                },
//                onGoBack = {
//                    navController.popBackStack()
//                },
//                dataStoreManager = dataStoreManager,
//                navController = navController
//            )
//        }
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
            WateringScreen(
                onGoBack = { navController.navigate(Screen.HomeScreen.route) },
                onGoToAddPlant = { navController.navigate(Screen.PlantAddScreen.route)},
                onGoToPlantConfiguration = { navController.navigate(Screen.PlantConfigurationScreen.route)},
                //addedPlants = addedPlants.value,
                dataStoreManager = dataStoreManager
            )
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

