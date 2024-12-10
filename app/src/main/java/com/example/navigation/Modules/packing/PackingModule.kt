package com.example.navigation.Modules.packing

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.R
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurPackingBlue


@Composable
fun PackingModule(
    onClick: () -> Unit,
    tripName: String?,
    packingPercentage: Int
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = OurPackingBlue,
        ),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "PACKING",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = OurBeige,
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (tripName == null) {
                    Row {
                        Text(
                            text = "you have no trips, click to add a trip :)",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left,
                            color = OurBeige,
                        )
                    }
                } else {
                    Text(
                        text = "next trip: $tripName",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        color = OurBeige,
                    )
                    Text(
                        text = "Packed: $packingPercentage%",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        color = OurBeige,
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.briefcase_2636186),
                contentDescription = "Suitcase",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
                    .height(110.dp)
                    .padding(16.dp)
            )
        }
    }
}

