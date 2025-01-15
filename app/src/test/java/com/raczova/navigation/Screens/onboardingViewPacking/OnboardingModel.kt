package com.raczova.navigation.Screens.onboardingViewPacking

import com.raczova.navigation.R
import org.junit.Assert.assertEquals
import org.junit.Test

class OnboardingModelTest {

    @Test
    fun testFirstOnboardingModel() {
        val model = OnboardingModel.First
        assertEquals(R.drawable.briefcase_2636186, model.image)
        assertEquals(R.string.first_onboarding_title, model.titleRes)
        assertEquals(R.string.first_onboarding_description, model.descriptionRes)
    }

    @Test
    fun testSecondOnboardingModel() {
        val model = OnboardingModel.Second
        assertEquals(R.drawable.secondscreen, model.image)
        assertEquals(R.string.second_onboarding_title, model.titleRes)
        assertEquals(R.string.second_onboarding_description, model.descriptionRes)
    }

    @Test
    fun testThirdOnboardingModel() {
        val model = OnboardingModel.Third
        assertEquals(R.drawable.thirdscreen, model.image)
        assertEquals(R.string.third_onboarding_title, model.titleRes)
        assertEquals(R.string.third_onboarding_description, model.descriptionRes)
    }

    @Test
    fun testFourthOnboardingModel() {
        val model = OnboardingModel.Fourth
        assertEquals(R.drawable.travel, model.image)
        assertEquals(R.string.fourth_onboarding_title, model.titleRes)
        assertEquals(R.string.fourth_onboarding_description, model.descriptionRes)
    }
}