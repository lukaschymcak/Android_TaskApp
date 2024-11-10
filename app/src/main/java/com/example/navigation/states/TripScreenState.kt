package com.example.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.example.navigation.models.Bag
import com.example.navigation.models.Item

object TripScreenState {
    @SuppressLint("MutableCollectionMutableState")
    private val bagList = mutableStateOf(mutableListOf<Bag>())



    fun getBags(): List<Bag> = bagList.value

    fun addBag(bag: Bag) {
        bagList.value.add(bag)
    }

    fun removeBag(bag: Bag) {
        bagList.value.remove(bag)
    }

    fun addItemToBag(bagName: String, item: Item) {
        bagList.value.find { it.getBagName() == bagName }?.addItem(item)
    }
}
