package com.example.navigation.models

class Packing (private val color: String, private var percentage: Int, private var arrayTrip: List<ItemModel>) {
    fun getColor(): String {
        return color
    }
    fun getPercentage(): Int {
        return percentage
    }
    fun getArrayTrip(): List<ItemModel> {
        return arrayTrip
    }
    fun setPercentage(percentage: Int) {
        this.percentage = percentage
    }
    fun setArrayTrip(trip: List<ItemModel>) {
        arrayTrip = trip
    }
    fun addItem(itemModel: ItemModel) {
        arrayTrip.plus(itemModel)
    }
    fun removeItem(itemModel: ItemModel) {
        arrayTrip.minus(itemModel)
    }


}