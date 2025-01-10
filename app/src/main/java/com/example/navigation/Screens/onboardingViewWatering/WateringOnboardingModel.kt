package com.example.navigation.Screens.onboardingViewWatering


import androidx.annotation.DrawableRes
import com.example.navigation.R

sealed class WateringOnboardingModel (
    @DrawableRes val image: Int,
    val title: String,
    val description: String){

    data object FirstW : WateringOnboardingModel(
        image = R.drawable.orchid,
        title = "WELCOME TO THE WATERING MODULE!",
        description = "Let me show you how it works :)"
    )
    data object SecondW : WateringOnboardingModel(
        image = R.drawable.monstera,
        title = "NEED HELP CARING FOR YOUR PLANTS?",
        description = "Here you can create a plant, set the watering frequency and see when it's time to water them!"
    )
    data object ThirdW : WateringOnboardingModel(
        image = R.drawable.zz_plant,
        title = "NEVER FORGET TO WATER YOUR PLANTS!",
        description = "Just add a plant, set the watering frequency and you're ready to go!"
    )
    data object FourthW : WateringOnboardingModel(
        image = R.drawable.spider_plant,
        title = "ENJOY!",
        description = "Remember, everything is still work in progress, so please let us know if you have any feedback. Thanks a lot!"
    )

}