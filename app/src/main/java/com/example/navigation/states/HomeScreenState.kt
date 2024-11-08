package com.example.navigation.states

import android.content.Context
import com.example.navigation.PreferencesHelper
import com.example.navigation.models.Bag
import com.example.navigation.models.Item
import com.example.navigation.models.Trip

object HomeScreenState {
    private var name: String? = null
    private var tripArray: MutableList<Trip> = mutableListOf()
    private var bagArray: MutableList<Bag> = mutableListOf()
    private var itemArray: MutableList<Item> = mutableListOf()


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
    fun getBagArray(): MutableList<Bag> {
        return bagArray
    }
    fun addBag(bag: Bag) {
        bagArray.add(bag)
    }
    fun removeBag(bag: Bag) {
        bagArray.remove(bag)
    }
    fun getItemArray(): MutableList<Item> {
        return itemArray
    }
    fun addItem(item: Item) {
        itemArray.add(item)
    }
    fun removeItem(item: Item) {
        itemArray.remove(item)
    }

}