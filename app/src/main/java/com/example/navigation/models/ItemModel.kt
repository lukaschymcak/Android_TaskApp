package com.example.navigation.models

import kotlinx.serialization.Serializable


@Serializable
data class ItemModel(
    private val itemName: String,
    var isChecked: Boolean = false
) {
    fun getItemName(): String = itemName
    fun getIsChecked(): Boolean = isChecked

    fun setIsChecked(value: Boolean) {
        isChecked = value
    }

}