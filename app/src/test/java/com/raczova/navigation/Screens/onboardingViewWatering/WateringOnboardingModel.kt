package com.raczova.navigation.Screens.onboardingViewWatering

import com.raczova.navigation.R
import org.junit.Assert.assertEquals
import org.junit.Test

class WateringOnboardingModelTest {

    @Test
    fun testFirstWOnboardingModel() {
        val model = WateringOnboardingModel.FirstW
        assertEquals(R.drawable.orchid, model.image)
        assertEquals(R.string.watering_first_title, model.titleRes)
        assertEquals(R.string.watering_first_description, model.descriptionRes)
    }

    @Test
    fun testSecondWOnboardingModel() {
        val model = WateringOnboardingModel.SecondW
        assertEquals(R.drawable.monstera, model.image)
        assertEquals(R.string.watering_second_title, model.titleRes)
        assertEquals(R.string.watering_second_description, model.descriptionRes)
    }

    @Test
    fun testThirdWOnboardingModel() {
        val model = WateringOnboardingModel.ThirdW
        assertEquals(R.drawable.zz_plant, model.image)
        assertEquals(R.string.watering_third_title, model.titleRes)
        assertEquals(R.string.watering_third_description, model.descriptionRes)
    }

    @Test
    fun testFourthWOnboardingModel() {
        val model = WateringOnboardingModel.FourthW
        assertEquals(R.drawable.spider_plant, model.image)
        assertEquals(R.string.watering_fourth_title, model.titleRes)
        assertEquals(R.string.watering_fourth_description, model.descriptionRes)
    }
}