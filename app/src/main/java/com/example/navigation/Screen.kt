package com.example.navigation

sealed interface Screen {


    @kotlinx.serialization.Serializable
    data class HomeScreen(val name: Int): Screen

    @kotlinx.serialization.Serializable
    data object PackingScreen: Screen

    @kotlinx.serialization.Serializable
    data object TripScreen: Screen

    @kotlinx.serialization.Serializable
    data object WelcomeScreen: Screen
}
