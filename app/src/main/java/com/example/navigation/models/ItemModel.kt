package com.example.navigation.models

class ItemModel(
    private val itemName: String,
    private var isChecked: Boolean = false
) {
    fun getItemName(): String = itemName
    fun getIsChecked(): Boolean = isChecked

    fun toggleChecked() {
        isChecked = !isChecked
    }

    fun setIsChecked(value: Boolean) {
        isChecked = value
    }
}