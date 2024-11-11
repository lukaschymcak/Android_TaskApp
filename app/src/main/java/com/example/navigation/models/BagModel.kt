package com.example.navigation.models

class BagModel(
    private val bagName: String,
    private var itemModels: MutableList<ItemModel>
) {
    fun getBagName(): String {
        return bagName
    }
    fun getItems(): MutableList<ItemModel> {
        return itemModels
    }
    fun addItem(itemModel: ItemModel) {
        itemModels.add(itemModel)
    }
}
