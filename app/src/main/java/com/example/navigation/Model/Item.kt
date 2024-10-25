package com.example.navigation.Model

class Item(
    val itemName: String,
    val isChecked: Boolean
) {
    fun getItemName(): String {
        return itemName
    }
    fun getIsChecked(): Boolean {
        return isChecked
    }
}