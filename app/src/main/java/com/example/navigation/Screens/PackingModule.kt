package com.example.navigation.Screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurPackingBlue


@Composable
fun PackingModuleExample(color: Color) {
    Card(
        onClick = {

        },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxSize()
            .wrapContentHeight(),
        border = BorderStroke(4.dp, OurPackingBlue)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "PACKING",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = OurPackingBlue
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "next trip: ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = OurPackingBlue
            )
            Text(
                text = "Chata Levice in 10 days ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = OurPackingBlue
            )
            Text(
                text = "packed: 70%",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = OurPackingBlue
            )
        }
    }
}
