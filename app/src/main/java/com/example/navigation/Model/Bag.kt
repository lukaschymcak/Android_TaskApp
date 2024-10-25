package com.example.navigation.Model

class Bag(
    val bagName: String,
    var items: MutableList<Item>
) {
    fun getBagName(): String {
        return bagName
    }
    fun getItems(): MutableList<Item> {
        return items
    }
}
