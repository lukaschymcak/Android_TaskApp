package com.example.navigation.Model

class Trip(
    private val name: String,
    private val startDate: String,
    private val endDate: String,
    private var arrayBag: List<Bag>,
    private var allItems: Int,
    private var allCheckedItems: Int,
    private var tripPercentage: Int
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
    fun getArrayBag(): List<Bag> {
        return arrayBag
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
    fun setArrayBag(bags: MutableList<Bag>) {
        arrayBag = bags
    }
    fun addBag(bag: Bag) {
        arrayBag.plus(bag)
    }
    fun removeBag(bag: Bag) {
        arrayBag.minus(bag)
    }

}