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
