package com.raczova.navigation.models.packing

import kotlinx.serialization.Serializable

@Serializable
data class BagModel(
    val bagName: String,
    var itemModels: MutableList<ItemModel> = mutableListOf()
) {

    fun deleteBag() {
        itemModels = mutableListOf()
    }

    fun addItem(item: ItemModel) {
        itemModels.add(item)
    }


}

