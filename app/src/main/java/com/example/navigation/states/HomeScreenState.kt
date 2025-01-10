package com.example.navigation.states

import android.content.Context
import android.content.SharedPreferences
import com.example.navigation.models.watering.PlantModel

object HomeScreenState {
    private var name: String? = null
    private var wasShown: Boolean = false
    private var selectedPlant: PlantModel? = null
    private var onboardingPackingShown: Boolean = false
    private var onboardingWateringShown: Boolean = false

    fun getName(context: Context): String {
        if (name == null) {
            name = PreferencesHelper.getName(context) ?: ""
        }
        return name ?: ""
    }

    fun setName(context: Context, newName: String) {
        name = newName
        PreferencesHelper.setName(context, newName)
    }
    fun getSelectedPlant(context: Context): PlantModel? {
        return selectedPlant
    }
    fun setSelectedPlant(context: Context, plant: PlantModel) {
        selectedPlant = plant
    }
    fun isOnboardingPackingShown(context: Context): Boolean {
        if (!onboardingPackingShown) {
            onboardingPackingShown = PreferencesHelper.isOnboardingPackingShown(context)
        }
        return onboardingPackingShown
    }

    fun setOnboardingPackingShown(context: Context, shown: Boolean) {
        onboardingPackingShown = shown
        PreferencesHelper.setOnboardingPackingShown(context, shown)
    }
}


object PreferencesHelper {
    private const val PREFS_NAME = "my_prefs"
    private const val KEY_NAME = "name"
    private const val KEY_WELCOME_SCREEN_SHOWN = "welcome_screen_shown"
    private const val KEY_ONBOARDING_PACKING_SHOWN = "onboarding_packing_shown"
    private const val KEY_ONBOARDING_WATERING_SHOWN = "onboarding_packing_shown"


    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setName(context: Context, newName: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_NAME, newName)
        editor.apply()
    }

    fun getName(context: Context): String? {
        return getPreferences(context).getString(KEY_NAME, null)
    }

    fun setWelcomeScreenShown(context: Context, shown: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(KEY_WELCOME_SCREEN_SHOWN, shown)
        editor.apply()
    }

    fun isWelcomeScreenShown(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_WELCOME_SCREEN_SHOWN, false)
    }
    fun isOnboardingPackingShown(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_ONBOARDING_PACKING_SHOWN, false)
    }
    fun setOnboardingPackingShown(context: Context, shown: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(KEY_ONBOARDING_PACKING_SHOWN, shown)
        editor.apply()
    }

}

