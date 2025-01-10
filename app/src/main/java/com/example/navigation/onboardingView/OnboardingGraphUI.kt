package com.example.navigation.onboardingView

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurPackingBlue

@Composable
fun OnboardingGraphUI(onboardingModel: OnboardingModel) {
    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = OurPackingBlue),

        ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center

            ) {
            Spacer(modifier = Modifier.size(50.dp))


            Text(
                text = onboardingModel.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                color = OurBeige,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(25.dp))

            Image(
                painter = painterResource(id = onboardingModel.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(50.dp, 0.dp)
                    .size(400.dp),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.size(25.dp))

            Text(
                text = onboardingModel.description,
                modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = OurBeige
            )
            Spacer(modifier = Modifier.fillMaxWidth().size(10.dp))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview1() {
    OnboardingGraphUI(OnboardingModel.First)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview2() {
    OnboardingGraphUI(OnboardingModel.Second)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview3() {
    OnboardingGraphUI(OnboardingModel.Third)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview4() {
    OnboardingGraphUI(OnboardingModel.Fourth)
}

