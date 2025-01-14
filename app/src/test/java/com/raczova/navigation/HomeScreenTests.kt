//package com.raczova.navigation
//
//import com.raczova.navigation.Screens.packing.calculatePackingPercentage
//import com.raczova.navigation.models.packing.BagModel
//import com.raczova.navigation.models.packing.ItemModel
//import com.raczova.navigation.models.packing.TripModel
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
//class HomeScreenTest {
//
//    @Test
//    fun calculatePackingPercentage_noItems_returnsHundred() {
//        val trip = TripModel(
//            arrayBagModel = emptyList(),
//            name = "Trip",
//            startDate = "2025-01-01",
//            endDate = "2025-01-02",
//            allItems = 0,
//            allCheckedItems = 0
//        )
//        val result = calculatePackingPercentage(trip)
//        assertEquals(100, result)
//    }
//
//    @Test
//    fun calculatePackingPercentage_allItemsChecked_returnsHundred() {
//        val trip = TripModel(
//            arrayBagModel = emptyList(),
//            name = "Trip",
//            startDate = "2025-01-01",
//            endDate = "2025-01-02",
//            allItems = 2,
//            allCheckedItems = 2
//        )
//        val result = calculatePackingPercentage(trip)
//        assertEquals(100, result)
//    }
//
//    @Test
//    fun calculatePackingPercentage_someItemsChecked_returnsCorrectPercentage() {
//        val trip = TripModel(
//            arrayBagModel = listOf(
//                BagModel(
//                    bagName = "Bag1",
//                    itemModels = mutableListOf(
//                        ItemModel(itemName = "Item1", isChecked = true),
//                        ItemModel(itemName = "Item2", isChecked = false)
//                    )
//                )
//            ),
//            name = "Trip",
//            startDate = "2025-01-01",
//            endDate = "2025-01-02",
//            allItems = 2,
//            allCheckedItems = 1
//        )
//        val result = calculatePackingPercentage(trip)
//        assertEquals(50, result)
//    }
//}