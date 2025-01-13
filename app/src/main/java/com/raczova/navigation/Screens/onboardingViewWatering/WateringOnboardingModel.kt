package com.raczova.navigation.Screens.onboardingViewWatering


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raczova.navigation.R

sealed class WateringOnboardingModel(
    @DrawableRes val image: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
) {
    data object FirstW : WateringOnboardingModel(
        image = R.drawable.orchid,
        titleRes = R.string.watering_first_title,
        descriptionRes = R.string.watering_first_description
    )
    data object SecondW : WateringOnboardingModel(
        image = R.drawable.monstera,
        titleRes = R.string.watering_second_title,
        descriptionRes = R.string.watering_second_description
    )
    data object ThirdW : WateringOnboardingModel(
        image = R.drawable.zz_plant,
        titleRes = R.string.watering_third_title,
        descriptionRes = R.string.watering_third_description
    )
    data object FourthW : WateringOnboardingModel(
        image = R.drawable.spider_plant,
        titleRes = R.string.watering_fourth_title,
        descriptionRes = R.string.watering_fourth_description
    )
}
