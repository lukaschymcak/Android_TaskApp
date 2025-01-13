package com.raczova.navigation.Screens.onboardingViewWatering


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raczova.navigation.ui.theme.OurBeige
import com.raczova.navigation.ui.theme.OurWateringGreen

@Composable
fun OnboardingGraphUI(wateringOnboardingModel: WateringOnboardingModel) {
    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = OurWateringGreen),

        ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.size(50.dp))


            Text(
                text = wateringOnboardingModel.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                color = OurBeige,
                fontWeight = FontWeight.Bold,
                lineHeight = 40.sp
            )
            Spacer(modifier = Modifier.size(25.dp))

            Image(
                painter = painterResource(id = wateringOnboardingModel.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp)
                    .size(400.dp),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.size(25.dp))

            Text(
                text = wateringOnboardingModel.description,
                modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = OurBeige
            )
            Spacer(modifier = Modifier.fillMaxWidth().size(10.dp))
        }
    }

}


