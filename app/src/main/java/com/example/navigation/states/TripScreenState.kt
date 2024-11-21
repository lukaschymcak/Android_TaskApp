package com.example.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.example.navigation.models.BagModel
import com.example.navigation.models.ItemModel

object TripScreenState {
    @SuppressLint("MutableCollectionMutableState")
    private val bagModelList = mutableStateOf(mutableListOf<BagModel>())



    fun getBags(): List<BagModel> = bagModelList.value

    fun addBag(bagModel: BagModel) {
        bagModelList.value.add(bagModel)
    }

    fun removeBag(bagModel: BagModel) {
        bagModelList.value.remove(bagModel)
    }

    fun addItemToBag(bagName: String, itemModel: ItemModel) {
        bagModelList.value.find { it.bagName == bagName }?.addItem(itemModel)
    }
}
