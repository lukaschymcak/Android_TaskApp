package com.example.mytaskapp

sealed interface Screen {
    @kotlinx.serialization.Serializable
    data object HomeScreen: Screen

    @kotlinx.serialization.Serializable
    data object PackingScreen: Screen

    @kotlinx.serialization.Serializable
    data object TripScreen: Screen
}
