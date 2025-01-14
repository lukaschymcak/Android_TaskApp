package com.raczova.navigation.models.packing

import org.junit.Assert.assertEquals
import org.junit.Test

class TripModelTest {

    @Test
    fun testSetTripPercentage() {
        val trip = TripModel(name = "Test Trip", startDate = "2023-01-01", endDate = "2023-01-10", allItems = 10, allCheckedItems = 5)
        trip.setTripPercentage()
        assertEquals(50, trip.tripPercentage)

        trip.allItems = 0
        trip.setTripPercentage()
        assertEquals(100, trip.tripPercentage)
    }

    @Test
    fun testCalculatePackingPercentage() {
        val item1 = ItemModel(itemName = "Item 1", isChecked = true)
        val item2 = ItemModel(itemName = "Item 2", isChecked = false)
        val bag1 = BagModel(bagName = "Bag 1", itemModels = mutableListOf(item1, item2))
        val trip = TripModel(name = "Test Trip", startDate = "2023-01-01", endDate = "2023-01-10", arrayBagModel = listOf(bag1))

        val packingPercentage = trip.calculatePackingPercentage(trip)
        assertEquals(50, packingPercentage)
    }
}