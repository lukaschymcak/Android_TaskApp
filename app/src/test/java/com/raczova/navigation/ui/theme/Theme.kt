//package com.raczova.navigation.ui.theme
//
//import android.os.Build
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class NavigationThemeTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testLightTheme() {
//        composeTestRule.setContent {
//            NavigationTheme(darkTheme = false, dynamicColor = false) {
//                androidx.compose.material3.Text(text = "Light Theme")
//            }
//        }
//
//        composeTestRule.onNodeWithText("Light Theme").assertIsDisplayed()
//    }
//
//    @Test
//    fun testDarkTheme() {
//        composeTestRule.setContent {
//            NavigationTheme(darkTheme = true, dynamicColor = false) {
//                androidx.compose.material3.Text(text = "Dark Theme")
//            }
//        }
//
//        composeTestRule.onNodeWithText("Dark Theme").assertIsDisplayed()
//    }
//
//    @Test
//    fun testDynamicLightTheme() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            composeTestRule.setContent {
//                NavigationTheme(darkTheme = false, dynamicColor = true) {
//                    // Add a simple UI element to verify the theme
//                    androidx.compose.material3.Text(text = "Dynamic Light Theme")
//                }
//            }
//
//            composeTestRule.onNodeWithText("Dynamic Light Theme").assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun testDynamicDarkTheme() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            composeTestRule.setContent {
//                NavigationTheme(darkTheme = true, dynamicColor = true) {
//                    androidx.compose.material3.Text(text = "Dynamic Dark Theme")
//                }
//            }
//
//            composeTestRule.onNodeWithText("Dynamic Dark Theme").assertIsDisplayed()
//        }
//    }
//}