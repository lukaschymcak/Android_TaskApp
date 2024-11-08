package com.example.navigation.models

class Item(
    private val itemName: String,
    private var isChecked: Boolean = false
) {
    fun getItemName(): String = itemName
    fun getIsChecked(): Boolean = isChecked

    fun toggleChecked() {
        isChecked = !isChecked
    }

    fun getCheckboxDrawable(): Int {
        return if (isChecked) {
            android.R.drawable.checkbox_on_background
        } else {
            android.R.drawable.checkbox_off_background
        }
    }
}