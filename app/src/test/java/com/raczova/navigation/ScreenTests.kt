package com.raczova.navigation

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString


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

class ScreenSerializationTest {

    private val json = Json { encodeDefaults = true }

    @Test
    fun testHomeScreenSerialization() {
        val screen: Screen = Screen.HomeScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testPackingScreenSerialization() {
        val screen: Screen = Screen.PackingScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testWelcomeScreenSerialization() {
        val screen: Screen = Screen.WelcomeScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testTripScreenSerialization() {
        val screen: Screen = Screen.TripScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testSettingsScreenSerialization() {
        val screen: Screen = Screen.SettingsScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testWateringScreenSerialization() {
        val screen: Screen = Screen.WateringScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testShoppingScreenSerialization() {
        val screen: Screen = Screen.ShoppingScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testRecipeScreenSerialization() {
        val screen: Screen = Screen.RecipeScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testPlantAddScreenSerialization() {
        val screen: Screen = Screen.PlantAddScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testPlantConfigurationScreenSerialization() {
        val screen: Screen = Screen.PlantConfigurationScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testOnboardingScreenSerialization() {
        val screen: Screen = Screen.OnboardingScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }

    @Test
    fun testWateringOnboardingScreenSerialization() {
        val screen: Screen = Screen.WateringOnboardingScreen
        val serialized = json.encodeToString(screen)
        val deserialized = json.decodeFromString<Screen>(serialized)
        assertEquals(screen, deserialized)
    }
}
