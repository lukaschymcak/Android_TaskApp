package com.example.navigation.Modules


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreen


@Composable
fun WateringModuleExample() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = OurGreen,
        ),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        //border = BorderStroke(5.dp, OurGreen)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "WATERING",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = OurBeige
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "next watering: ",
                fontSize = 20.sp,
                //fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = OurBeige
            )
            Text(
                text = "today : 5 plants ",
                fontSize = 20.sp,
                //fontWeight = FontWeight.Bold,

                textAlign = TextAlign.Left,
                color = OurBeige
            )
            Text(
                text = "tommorrow: 3 plants",
                fontSize = 20.sp,
                //fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = OurBeige
            )
        }
    }
}