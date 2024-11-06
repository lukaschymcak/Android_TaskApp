package com.example.navigation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.HomeScreenState
import com.example.navigation.Modules.BagModule
import com.example.navigation.Modules.TripModule
import com.example.navigation.models.Trip
import com.example.navigation.ui.theme.OurPackingBlue

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TripScreen(
    onGoBack: () -> Unit,
) {
    val trip = Trip("Paris", "2022-01-01", "2022-01-10")
    val scrollState = rememberScrollState()
    val bagList = remember { mutableStateOf(HomeScreenState.getBagArray().toMutableList()) }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent)
            .verticalScroll(scrollState),
        Arrangement.Top,
        Alignment.CenterHorizontally,

        ) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = (-120).dp)
                    .clickable { onGoBack() },
                tint = OurPackingBlue
            )

            Text(
                text = trip.getName(),
                color = OurPackingBlue,
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .paddingFromBaseline(top = 35.dp)
                    .offset(x = (-30).dp),
            )

        }
        HorizontalDivider(
            modifier = Modifier.width(355.dp),
            thickness = 2.dp,
            color = OurPackingBlue
        )
        Text(
            text = "${trip.getStartDate()} - ${trip.getEndDate()}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            color = OurPackingBlue,
            modifier = Modifier.padding(8.dp)
        )
        Card {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "Bags",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        color = OurPackingBlue
                    )
                    if (bagList.value.isEmpty()) {
                        Text(
                            text = "No trips yet, add a trip :)",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(24.dp)
                        )
                    } else {
                        bagList.value.forEach { trip ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = OurPackingBlue,
                                ),
                            ) {
                                BagModule()
                            }
                        }
                    }
                }
            }
        }
    }
}
