package com.example.navigation.models

class TripModel(
    private val name: String,
    private val startDate: String,
    private val endDate: String,
    private var arrayBagModel: List<BagModel>? = null,
    private var allItems: Int = 0,
    private var allCheckedItems: Int = 0,
    private var tripPercentage: Int = 0
) {
    fun getName(): String {
        return name
    }
    fun getStartDate(): String {
        return startDate
    }
    fun getEndDate(): String {
        return endDate
    }
    fun getArrayBag(): List<BagModel>? {
        return arrayBagModel
    }
    fun getAllItems(): Int {
        return allItems
    }
    fun getAllCheckedItems(): Int {
        return allCheckedItems
    }
    fun getTripPercentage(): Int {
        return tripPercentage
    }
    fun setTripPercentage(percentage: Int) {
        tripPercentage = percentage
    }
    fun setAllItems(items: Int) {
        allItems = items
    }
    fun setAllCheckedItems(checkedItems: Int) {
        allCheckedItems = checkedItems
    }
    fun setArrayBag(bagModels: MutableList<BagModel>) {
        arrayBagModel = bagModels
    }
    fun addBag(bagModel: BagModel) {
        arrayBagModel?.plus(bagModel)
    }
    fun removeBag(bagModel: BagModel) {
        arrayBagModel?.minus(bagModel)
    }

}