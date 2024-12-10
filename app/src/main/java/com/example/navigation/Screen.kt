package com.example.navigation

sealed interface Screen {

    @kotlinx.serialization.Serializable
    data object HomeScreen: Screen

    @kotlinx.serialization.Serializable
    data object PackingScreen: Screen

    @kotlinx.serialization.Serializable
    data object WelcomeScreen: Screen

    @kotlinx.serialization.Serializable
    data object TripScreen: Screen

    @kotlinx.serialization.Serializable
    data object SettingsScreen: Screen

    @kotlinx.serialization.Serializable
    data object WateringScreen: Screen

    @kotlinx.serialization.Serializable
    data object ShoppingScreen: Screen

    @kotlinx.serialization.Serializable
    data object RecipeScreen: Screen

    @kotlinx.serialization.Serializable
    data object PlantAddScreen: Screen





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
    }