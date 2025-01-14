package com.raczova.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenTest {

    @Test
    fun testHomeScreenRoute() {
        val screen: Screen = Screen.HomeScreen
        assertEquals("home_screen", screen.route)
    }

    @Test
    fun testPackingScreenRoute() {
        val screen: Screen = Screen.PackingScreen
        assertEquals("packing_screen", screen.route)
    }

    @Test
    fun testWelcomeScreenRoute() {
        val screen: Screen = Screen.WelcomeScreen
        assertEquals("welcome_screen", screen.route)
    }

    @Test
    fun testTripScreenRoute() {
        val screen: Screen = Screen.TripScreen
        assertEquals("trip_screen", screen.route)
    }

    @Test
    fun testSettingsScreenRoute() {
        val screen: Screen = Screen.SettingsScreen
        assertEquals("settings_screen", screen.route)
    }

    @Test
    fun testWateringScreenRoute() {
        val screen: Screen = Screen.WateringScreen
        assertEquals("watering_screen", screen.route)
    }

    @Test
    fun testShoppingScreenRoute() {
        val screen: Screen = Screen.ShoppingScreen
        assertEquals("shopping_screen", screen.route)
    }

    @Test
    fun testRecipeScreenRoute() {
        val screen: Screen = Screen.RecipeScreen
        assertEquals("recipe_screen", screen.route)
    }

    @Test
    fun testPlantAddScreenRoute() {
        val screen: Screen = Screen.PlantAddScreen
        assertEquals("plant_add_screen", screen.route)
    }

    @Test
    fun testPlantConfigurationScreenRoute() {
        val screen: Screen = Screen.PlantConfigurationScreen
        assertEquals("plant_configuration_screen", screen.route)
    }

    @Test
    fun testOnboardingScreenRoute() {
        val screen: Screen = Screen.OnboardingScreen
        assertEquals("onboarding_screen", screen.route)
    }

    @Test
    fun testWateringOnboardingScreenRoute() {
        val screen: Screen = Screen.WateringOnboardingScreen
        assertEquals("watering_onboarding_screen", screen.route)
    }
}