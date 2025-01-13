package com.raczova.navigation.Screens.onboardingViewPacking

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raczova.navigation.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
) {
    data object First : OnboardingModel(
        image = R.drawable.briefcase_2636186,
        titleRes = R.string.first_onboarding_title,
        descriptionRes = R.string.first_onboarding_description
    )
    data object Second : OnboardingModel(
        image = R.drawable.secondscreen,
        titleRes = R.string.second_onboarding_title,
        descriptionRes = R.string.second_onboarding_description
    )
    data object Third : OnboardingModel(
        image = R.drawable.thirdscreen,
        titleRes = R.string.third_onboarding_title,
        descriptionRes = R.string.third_onboarding_description
    )
    data object Fourth : OnboardingModel(
        image = R.drawable.travel,
        titleRes = R.string.fourth_onboarding_title,
        descriptionRes = R.string.fourth_onboarding_description
    )
}
