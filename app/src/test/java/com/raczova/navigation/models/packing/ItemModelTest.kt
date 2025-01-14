package com.raczova.navigation.models.packing

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ItemModelTest {

    @Test
    fun testGetItemName() {
        val item = ItemModel(itemName = "Test Item")
        assertEquals("Test Item", item.getItemName())
    }

    @Test
    fun testGetIsChecked() {
        val item = ItemModel(itemName = "Test Item", isChecked = true)
        assertTrue(item.getIsChecked())
    }

    @Test
    fun testSetIsChecked() {
        val item = ItemModel(itemName = "Test Item")
        item.setIsChecked(true)
        assertTrue(item.getIsChecked())
        item.setIsChecked(false)
        assertFalse(item.getIsChecked())
    }
}