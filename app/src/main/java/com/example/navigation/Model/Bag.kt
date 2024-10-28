package com.example.navigation.Model

class Bag(
    private val bagName: String,
    private var items: MutableList<Item>
) {
    fun getBagName(): String {
        return bagName
    }
    fun getItems(): MutableList<Item> {
        return items
    }
}
