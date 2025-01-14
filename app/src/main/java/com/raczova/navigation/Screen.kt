package com.raczova.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object HomeScreen: Screen

    @Serializable
    data object PackingScreen: Screen

    @Serializable
    data object WelcomeScreen: Screen

    @Serializable
    data object TripScreen: Screen

    @Serializable
    data object SettingsScreen: Screen

    @Serializable
    data object WateringScreen: Screen

    @Serializable
    data object ShoppingScreen: Screen

    @Serializable
    data object RecipeScreen: Screen

    @Serializable
    data object PlantAddScreen: Screen

    @Serializable
    data object PlantConfigurationScreen: Screen

    @Serializable
    data object OnboardingScreen: Screen

    @Serializable
    data object WateringOnboardingScreen: Screen


}
val Screen.route: String
    get() = when (this) {
        Screen.HomeScreen -> "home_screen"
        Screen.PackingScreen -> "packing_screen"
        Screen.WelcomeScreen -> "welcome_screen"
        Screen.TripScreen -> "trip_screen"
        Screen.SettingsScreen -> "settings_screen"
        Screen.WateringScreen -> "watering_screen"
        Screen.ShoppingScreen -> "shopping_screen"
        Screen.RecipeScreen -> "recipe_screen"
        Screen.PlantAddScreen -> "plant_add_screen"
        Screen.PlantConfigurationScreen -> "plant_configuration_screen"
        Screen.OnboardingScreen -> "onboarding_screen"
        Screen.WateringOnboardingScreen -> "watering_onboarding_screen"
    }


fun getStartDestination(welcomeScreenShown: Boolean): String {
    return if (welcomeScreenShown) {
        Screen.HomeScreen.route
    } else {
        Screen.WelcomeScreen.route
    }
}