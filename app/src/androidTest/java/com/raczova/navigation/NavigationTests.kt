//package com.raczova.navigation
//
//import androidx.activity.compose.setContent
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextClearance
//import androidx.compose.ui.test.performTextInput
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.raczova.navigation.states.PreferencesHelper
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@OptIn(ExperimentalCoroutinesApi::class)
//@RunWith(AndroidJUnit4::class)
//class NavigationTest {
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    private val dataStoreManager by lazy { DataStoreManager(composeTestRule.activity) }
//
//
//    @Before
//    fun setUp() {
//        runBlocking {
//            dataStoreManager.clearData()
//            PreferencesHelper.clearOnboardingState(composeTestRule.activity)
//        }
//    }
//
//    @Test
//    fun nameInputAndChange() {
//        composeTestRule.activity.setContent {
//            Navigation(
//                startDestination = Screen.WelcomeScreen.route,
//                dataStoreManager = dataStoreManager
//            )
//        }
//        val name = "test"
//        val editedName = "test2"
//        val welcomeTitle = composeTestRule.activity.getString(R.string.welcome_screen_title)
//        val homescreenTitle = composeTestRule.activity.getString(R.string.hello_name)
//        val enterButtonText = composeTestRule.activity.getString(R.string.enter)
//        val saveButton = composeTestRule.activity.getString(R.string.change_name_button)
//        val settingsText = composeTestRule.activity.getString(R.string.settings_title)
//
//
//        composeTestRule.onNodeWithText(welcomeTitle).assertExists()
//        composeTestRule.onNodeWithText("").performTextInput(name)
//        composeTestRule.onNodeWithText(enterButtonText).performClick()
//        composeTestRule.onNodeWithText("$homescreenTitle $name!").assertExists()
//
//        composeTestRule.onNodeWithContentDescription("Settings").performClick()
//        composeTestRule.onNodeWithText(settingsText).assertExists()
//
//        composeTestRule.onNodeWithText(name).performTextClearance()
//        composeTestRule.onNodeWithText("").performTextInput(editedName)
//        composeTestRule.onNodeWithText(saveButton).performClick()
//        composeTestRule.onNodeWithText("$homescreenTitle $editedName!").assertExists()
//
//    }
//
//
//
//    @Test
//    fun navigationFromHomeToSettingScreenAndBack() {
//        composeTestRule.activity.setContent {
//            Navigation(
//                startDestination = Screen.HomeScreen.route,
//                dataStoreManager = dataStoreManager
//            )
//        }
//        val createdText = composeTestRule.activity.getString(R.string.created_by)
//        val settingsText = composeTestRule.activity.getString(R.string.settings_title)
//
//        composeTestRule.onNodeWithText(createdText).assertExists()
//        composeTestRule.onNodeWithContentDescription("Settings").performClick()
//        composeTestRule.onNodeWithText(settingsText).assertExists()
//        composeTestRule.onNodeWithContentDescription("Back home").performClick()
//        composeTestRule.onNodeWithText(createdText).assertExists()
//    }
//
//    @Test
//    fun navigationFromHomeToPackingScreenOnboarding() {
//        composeTestRule.activity.setContent {
//            Navigation(
//                startDestination = Screen.HomeScreen.route,
//                dataStoreManager = dataStoreManager
//            )
//        }
//        val createdText = composeTestRule.activity.getString(R.string.created_by)
//        val packingTitle = composeTestRule.activity.getString(R.string.packing_title)
//        val onboardingTitle = composeTestRule.activity.getString(R.string.first_onboarding_title)
//        val secondOnboardingTitle = composeTestRule.activity.getString(R.string.second_onboarding_title)
//        val thirdOnboardingTitle = composeTestRule.activity.getString(R.string.third_onboarding_title)
//        val fourthOnboardingTitle = composeTestRule.activity.getString(R.string.fourth_onboarding_title)
//
//        composeTestRule.onNodeWithText(createdText).assertExists()
//
//        composeTestRule.onNodeWithContentDescription("Suitcase").performClick()
//        composeTestRule.onNodeWithText(packingTitle).assertExists()
//    }
//
//    @Test
//    fun navigationFromHomeToWateringScreenOnboarding() {
//        composeTestRule.activity.setContent {
//            Navigation(
//                startDestination = Screen.HomeScreen.route,
//                dataStoreManager = dataStoreManager
//            )
//        }
//        val createdText = composeTestRule.activity.getString(R.string.created_by)
//        val wateringTitle = composeTestRule.activity.getString(R.string.watering_title)
//
//        composeTestRule.onNodeWithText(createdText).assertExists()
//
//        composeTestRule.onNodeWithContentDescription("Plant").performClick()
//        composeTestRule.onNodeWithText(wateringTitle).assertExists()
//
//    }
//
//    @Test
//    fun addingTrip() {
//        composeTestRule.activity.setContent {
//            Navigation(
//                startDestination = Screen.HomeScreen.route,
//                dataStoreManager = dataStoreManager
//            )
//        }
//        val createdText = composeTestRule.activity.getString(R.string.created_by)
//        val packingTitle = composeTestRule.activity.getString(R.string.packing_title)
//        val onboardingTitle = composeTestRule.activity.getString(R.string.first_onboarding_title)
//        val secondOnboardingTitle = composeTestRule.activity.getString(R.string.second_onboarding_title)
//        val thirdOnboardingTitle = composeTestRule.activity.getString(R.string.third_onboarding_title)
//        val fourthOnboardingTitle = composeTestRule.activity.getString(R.string.fourth_onboarding_title)
//        val addTripButton = composeTestRule.activity.getString(R.string.add_trip_button)
//        val tripFrom = composeTestRule.activity.getString(R.string.select_date_from)
//        val tripTo = composeTestRule.activity.getString(R.string.select_date_to)
//        val saveButton = composeTestRule.activity.getString(R.string.add_trip_action)
//
//        composeTestRule.onNodeWithText(createdText).assertExists()
//
//        composeTestRule.onNodeWithContentDescription("Suitcase").performClick()
//        composeTestRule.onNodeWithText(onboardingTitle).assertExists()
//
//        composeTestRule.onNodeWithText("Next").performClick()
//        composeTestRule.onNodeWithText(secondOnboardingTitle).assertExists()
//
//        composeTestRule.onNodeWithText("Next").performClick()
//        composeTestRule.onNodeWithText(thirdOnboardingTitle).assertExists()
//
//        composeTestRule.onNodeWithText("Next").performClick()
//        composeTestRule.onNodeWithText(fourthOnboardingTitle).assertExists()
//
//        composeTestRule.onNodeWithText("Start").performClick()
//        composeTestRule.onNodeWithText(packingTitle).assertExists()
//
//        composeTestRule.onNodeWithText(addTripButton).performClick()
//        composeTestRule.onNodeWithText(tripFrom).assertExists()
//
//        composeTestRule.onNodeWithText("").performTextClearance()
//        composeTestRule.onNodeWithText("").performTextInput("Rome")
//        composeTestRule.onNodeWithText(tripFrom).performClick()
//
//
//    }
//
//
//}