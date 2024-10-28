package com.example.navigation

import android.content.Context

object HomeScreenState {
    private var name: String? = null

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

    private var wasShown: Boolean = false

    fun getWasShown(): Boolean {
        return wasShown
    }

    fun setWasShown(isShown: Boolean) {
        wasShown = isShown
    }
}