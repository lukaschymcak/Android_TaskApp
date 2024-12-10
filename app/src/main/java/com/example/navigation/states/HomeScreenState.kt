package com.example.navigation.states

import android.content.Context
import android.content.SharedPreferences

object HomeScreenState {
    private var name: String? = null
    private var wasShown: Boolean = false

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
}

object PreferencesHelper {
    private const val PREFS_NAME = "my_prefs"
    private const val KEY_NAME = "name"
    private const val KEY_WELCOME_SCREEN_SHOWN = "welcome_screen_shown"

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
}

