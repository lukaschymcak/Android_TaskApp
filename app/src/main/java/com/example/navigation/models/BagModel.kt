package com.example.navigation.models

import kotlinx.serialization.Serializable

@Serializable
data class BagModel(
     val bagName: String,
     var itemModels: List<ItemModel> = emptyList()
) {

    fun addItem(itemModel: ItemModel) {
        itemModels = itemModels + itemModel
    }

    fun deleteBag() {
        itemModels = emptyList()
    }


}

