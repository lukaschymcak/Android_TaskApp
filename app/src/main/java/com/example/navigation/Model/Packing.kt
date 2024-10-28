package com.example.navigation.Model

class Packing (private val color: String, private var percentage: Int, private var arrayTrip: List<Item>) {
    fun getColor(): String {
        return color
    }
    fun getPercentage(): Int {
        return percentage
    }
    fun getArrayTrip(): List<Item> {
        return arrayTrip
    }
    fun setPercentage(percentage: Int) {
        this.percentage = percentage
    }
    fun setArrayTrip(trip: List<Item>) {
        arrayTrip = trip
    }
    fun addItem(item: Item) {
        arrayTrip.plus(item)
    }
    fun removeItem(item: Item) {
        arrayTrip.minus(item)
    }


}