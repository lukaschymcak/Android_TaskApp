package com.raczova.navigation.models.packing

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BagModelTest {

    @Test
    fun testAddItem() {
        val bag = BagModel(bagName = "Test Bag")
        val item = ItemModel(itemName = "Test Item")
        bag.addItem(item)
        assertEquals(1, bag.itemModels.size)
        assertEquals("Test Item", bag.itemModels[0].getItemName())
    }

    @Test
    fun testDeleteBag() {
        val bag = BagModel(bagName = "Test Bag")
        val item = ItemModel(itemName = "Test Item")
        bag.addItem(item)
        assertEquals(1, bag.itemModels.size)
        bag.deleteBag()
        assertTrue(bag.itemModels.isEmpty())
    }
}