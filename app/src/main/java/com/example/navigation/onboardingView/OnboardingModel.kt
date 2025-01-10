package com.example.navigation.onboardingView

import androidx.annotation.DrawableRes
import com.example.navigation.R

sealed class OnboardingModel (
    @DrawableRes val image: Int,
    val title: String,
    val description: String){

    data object First : OnboardingModel(
        image = R.drawable.briefcase_2636186,
        title = "WELCOME TO THE PACKING MODULE!",
        description = "Let me show you how it works :)"
    )
    data object Second : OnboardingModel(
        image = R.drawable.secondscreen,
        title = "GOING ON A TRIP?",
        description = "Here you can create a trip, set the destination and the dates and start packing!"
    )
    data object Third : OnboardingModel(
        image = R.drawable.thirdscreen,
        title = "DON'T FORGET ANYTHING!",
        description = "Just add a bag, put in the items you want to pack and you're ready to go!"
    )
    data object Fourth : OnboardingModel(
        image = R.drawable.travel,
        title = "ENJOY!",
        description = "Remember, everything is still work in progress, so please let us know if you have any feedback. Thanks a lot!"
    )

}