package com.raczova.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val dataStoreManager by lazy { DataStoreManager(composeTestRule.activity) }

    @Test
    fun navigationFromHomeToSettingScreenAndBack() {
        composeTestRule.activity.setContent {
            Navigation(
                startDestination = Screen.HomeScreen.route,
                dataStoreManager = dataStoreManager
            )
        }
        val createdText = composeTestRule.activity.getString(R.string.created_by)
        val settingsText = composeTestRule.activity.getString(R.string.settings_title)

        composeTestRule.onNodeWithText(createdText).assertExists()
        composeTestRule.onNodeWithContentDescription("Settings").performClick()
        composeTestRule.onNodeWithText(settingsText).assertExists()
        composeTestRule.onNodeWithContentDescription("Back home").performClick()
        composeTestRule.onNodeWithText(createdText).assertExists()
    }

    @Test
    fun navigationFromHomeToPackingScreenOnboarding() {
        composeTestRule.activity.setContent {
            Navigation(
                startDestination = Screen.HomeScreen.route,
                dataStoreManager = dataStoreManager
            )
        }
        val createdText = composeTestRule.activity.getString(R.string.created_by)
        val packingTitle = composeTestRule.activity.getString(R.string.packing_title)
        val onboardingTitle = composeTestRule.activity.getString(R.string.first_onboarding_title)
        val secondOnboardingTitle = composeTestRule.activity.getString(R.string.second_onboarding_title)
        val thirdOnboardingTitle = composeTestRule.activity.getString(R.string.third_onboarding_title)
        val fourthOnboardingTitle = composeTestRule.activity.getString(R.string.fourth_onboarding_title)

        composeTestRule.onNodeWithText(createdText).assertExists()

        composeTestRule.onNodeWithContentDescription("Suitcase").performClick()
        composeTestRule.onNodeWithText(onboardingTitle).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(secondOnboardingTitle).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(thirdOnboardingTitle).assertExists()

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText(fourthOnboardingTitle).assertExists()

        composeTestRule.onNodeWithText("Start").performClick()
        composeTestRule.onNodeWithText(packingTitle).assertExists()



    }
}