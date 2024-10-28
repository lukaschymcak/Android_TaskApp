package com.example.navigation

sealed interface Screen {


    @kotlinx.serialization.Serializable
    data object HomeScreen: Screen

    @kotlinx.serialization.Serializable
    data object PackingScreen: Screen

    @kotlinx.serialization.Serializable
    data object TripAddScreen: Screen

    @kotlinx.serialization.Serializable
    data object WelcomeScreen: Screen
}
val Screen.route: String
    get() = when (this) {
        Screen.HomeScreen -> "home_screen"
        Screen.PackingScreen -> "packing_screen"
        Screen.TripAddScreen -> "trip_add_screen"
        Screen.WelcomeScreen -> "welcome_screen"
    }