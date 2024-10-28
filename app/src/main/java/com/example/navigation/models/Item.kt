package com.example.navigation.models

class Item(
    private val itemName: String,
    private var isChecked: Boolean,

) {
    fun getItemName(): String {
        return itemName
    }
    fun getIsChecked(): Boolean {
        return isChecked
    }
    fun toggleChecked() {
        isChecked = if (isChecked) {
            false
        } else {
            true
        }
    }
    fun toggleCheckedImg(): Int {
        return if (isChecked) {
            android.R.drawable.checkbox_on_background
        } else {
            android.R.drawable.checkbox_off_background
        }
    }
}