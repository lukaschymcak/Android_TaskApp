package com.raczova.navigation

import android.content.Context
import android.content.SharedPreferences
import com.raczova.navigation.models.watering.HouseLocation
import com.raczova.navigation.models.watering.PlantModel
import com.raczova.navigation.states.HomeScreenState
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeScreenStateTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setUp() {
        context = mockk(relaxed = true)
        sharedPreferences = mockk(relaxed = true)
        editor = mockk(relaxed = true)

        every { context.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.putBoolean(any(), any()) } returns editor
        every { editor.apply() } just Runs
    }

    @Test
    fun getName_returnsStoredName() {
        every { sharedPreferences.getString("name", null) } returns "John Doe"
        val name = HomeScreenState.getName(context)
        assertEquals("John Doe", name)
    }

    @Test
    fun setName_storesName() {
        HomeScreenState.setName(context, "Jane Doe")
        verify { editor.putString("name", "Jane Doe") }
    }
    @Test
    fun getSelectedPlant_returnsSelectedPlant() {
        val plant = PlantModel(
            "Rose",
            description = "",
            location = HouseLocation.BEDROOM,
            frequency = 2,
            water = "",
            image = R.drawable.monstera,
            watered = false
        )
        HomeScreenState.setSelectedPlant(context, plant)
        val selectedPlant = HomeScreenState.getSelectedPlant(context)
        assertEquals(plant, selectedPlant)
    }

    @Test
    fun isOnboardingPackingShown_returnsStoredValue() {
        every { sharedPreferences.getBoolean("onboarding_packing_shown", false) } returns true
        val result = HomeScreenState.isOnboardingPackingShown(context)
        assertEquals(true, result)
    }

    @Test
    fun setOnboardingPackingShown_storesValue() {
        HomeScreenState.setOnboardingPackingShown(context, true)
        verify { editor.putBoolean("onboarding_packing_shown", true) }
    }

    @Test
    fun isOnboardingWateringShown_returnsStoredValue() {
        every { sharedPreferences.getBoolean("onboarding_watering_shown", false) } returns true
        val result = HomeScreenState.isOnboardingWateringShown(context)
        assertEquals(true, result)
    }

    @Test
    fun setOnboardingWateringShown_storesValue() {
        HomeScreenState.setOnboardingWateringShown(context, true)
        verify { editor.putBoolean("onboarding_watering_shown", true) }
    }
}

