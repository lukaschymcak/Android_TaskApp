package com.example.navigation.Screens.onboardingViewWatering

import com.example.navigation.Screens.onboardingViewPacking.ButtonUI
import com.example.navigation.Screens.onboardingViewPacking.IndicatorUI
import com.example.navigation.Screens.onboardingViewPacking.OnboardingModel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurWateringGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WateringOnboardingScreen(onFinished: () -> Unit) {

    val pages = listOf(
        WateringOnboardingModel.FirstW, WateringOnboardingModel.SecondW, WateringOnboardingModel.ThirdW, WateringOnboardingModel.FourthW
    )

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }
    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Next")
                3 -> listOf("Back", "Start")
                else -> listOf("", "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart) {
                if (buttonState.value[0].isNotEmpty()) {
                    ButtonUI (text = buttonState.value[0],
                        backgroundColor = Color.Transparent,
                        textColor = Color.Gray) {
                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center) {
                IndicatorUI(pageSize = pages.size, currentPage = pagerState.currentPage)
            }

            Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd) {
                ButtonUI (text = buttonState.value[1],
                    backgroundColor = OurWateringGreen,
                    textColor = OurBeige) {
                    scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinished()
                        }
                    }
                }
            }

        }
    }, content = {
        Column(Modifier.padding(it),
            verticalArrangement = Arrangement.Center,) {
            HorizontalPager(state = pagerState) { index ->
                com.example.navigation.Screens.onboardingViewWatering.OnboardingGraphUI(
                    wateringOnboardingModel = pages[index]
                )
            }
        }
    })


}

