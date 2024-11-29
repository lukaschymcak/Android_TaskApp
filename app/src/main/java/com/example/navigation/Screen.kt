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



}
val Screen.route: String
    get() = when (this) {
        Screen.HomeScreen -> "home_screen"
        Screen.PackingScreen -> "packing_screen"
        Screen.WelcomeScreen -> "welcome_screen"
        Screen.TripScreen -> "trip_screen"
        Screen.SettingsScreen -> "settings_screen"
    }