package com.raczova.navigation

import junit.framework.Assert.assertEquals
import org.junit.Test


class NavigationUtilsTest {

    @Test
    fun testGetStartDestination_whenWelcomeScreenShown() {
        val startDestination = getStartDestination(true)
        assertEquals(Screen.HomeScreen.route, startDestination)
    }

    @Test
    fun testGetStartDestination_whenWelcomeScreenNotShown() {
        val startDestination = getStartDestination(false)
        assertEquals(Screen.WelcomeScreen.route, startDestination)
    }
}