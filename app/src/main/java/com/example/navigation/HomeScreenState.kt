package com.example.navigation

import android.content.Context
import com.example.navigation.models.Trip

object HomeScreenState {
    private var name: String? = null
    private var tripArray: MutableList<Trip> = mutableListOf()

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

    fun getTripArray(): MutableList<Trip> {
        return tripArray
    }
    fun addTrip(trip: Trip) {
        tripArray.add(trip)
    }
    fun removeTrip(trip: Trip) {
        tripArray.remove(trip)
    }

}