package com.example.navigation.states

import android.content.Context
import com.example.navigation.PreferencesHelper
import com.example.navigation.models.BagModel
import com.example.navigation.models.ItemModel
import com.example.navigation.models.TripModel

object HomeScreenState {
    private var name: String? = null
    private var tripModelArray: MutableList<TripModel> = mutableListOf()
    private var bagModelArray: MutableList<BagModel> = mutableListOf()
    private var itemModelArray: MutableList<ItemModel> = mutableListOf()


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

    fun getTripArray(): MutableList<TripModel> {
        return tripModelArray
    }
    fun addTrip(tripModel: TripModel) {
        tripModelArray.add(tripModel)
    }
    fun removeTrip(tripModel: TripModel) {
        tripModelArray.remove(tripModel)
    }
    fun getBagArray(): MutableList<BagModel> {
        return bagModelArray
    }
    fun addBag(bagModel: BagModel) {
        bagModelArray.add(bagModel)
    }
    fun removeBag(bagModel: BagModel) {
        bagModelArray.remove(bagModel)
    }
    fun getItemArray(): MutableList<ItemModel> {
        return itemModelArray
    }
    fun addItem(itemModel: ItemModel) {
        itemModelArray.add(itemModel)
    }
    fun removeItem(itemModel: ItemModel) {
        itemModelArray.remove(itemModel)
    }

}