package com.example.navigation.states

import android.content.Context
import com.example.navigation.PreferencesHelper

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

    fun setWasShown(isShown: Boolean) {
        wasShown = isShown
    }
}