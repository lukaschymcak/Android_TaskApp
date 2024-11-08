package com.example.navigation

import androidx.compose.runtime.mutableStateOf
import com.example.navigation.models.Bag
import com.example.navigation.models.Item

object TripScreenState {
    private val bagList = mutableStateOf(mutableListOf<Bag>())

    fun getBags(): List<Bag> = bagList.value

    fun addBag(bag: Bag) {
        bagList.value = (bagList.value + bag).toMutableList()
    }
    fun removeBag(bag: Bag) {
        bagList.value = (bagList.value - bag).toMutableList()
    }
    fun addItemToBag(bagName: String, item: Item) {
        val updatedBags = bagList.value.map { bag ->
            if (bag.getBagName() == bagName) {
                Bag(bag.getBagName(), bag.getItems().apply { add(item) })
            } else bag
        }
        bagList.value = updatedBags.toMutableList()
    }
}