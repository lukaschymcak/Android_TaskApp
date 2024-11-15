package com.example.navigation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.states.HomeScreenState
import com.example.navigation.Modules.BagModule
import com.example.navigation.models.BagModel
import com.example.navigation.models.ItemModel
import com.example.navigation.models.TripModel
import com.example.navigation.ui.theme.OurPackingBlue

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TripScreen(
    onGoBack: () -> Unit,
) {
    val tripModel = TripModel("Paris", "2022-01-01", "2022-01-10")
    val scrollState = rememberScrollState()
    val bagList = remember { mutableStateOf(HomeScreenState.getBagArray().toMutableList()) }
    val newBagName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
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
                text = tripModel.getName(),
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
            text = "${tripModel.getStartDate()} - ${tripModel.getEndDate()}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = OurPackingBlue,
            modifier = Modifier.padding(8.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = OurPackingBlue),
            modifier = Modifier.padding(10.dp)
        ) {
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
                        text = "BAGS",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }

                if (bagList.value.isEmpty()) {
                    Text(
                        text = "No bags yet, add a bag :)",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    bagList.value.forEach { bag ->
                        BagModule(
                            bagModel = bag,
                            onItemCheckedChange = { item ->
                                item.toggleChecked()
                            },
                            onAddItem = { newItemName ->
                                bag.addItem(ItemModel(newItemName, false))
                                bagList.value = bagList.value.toMutableList()
                            }
                        )
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            TextField(
                value = newBagName.value,
                onValueChange = { newBagName.value = it },
                label = { Text("Bag name") },
                modifier = Modifier.weight(1f).padding(8.dp),
                shape = RoundedCornerShape(15.dp),
            )
            Button(
                onClick = {
                    if (newBagName.value.isNotBlank()) {
                        val newBagModel = BagModel(newBagName.value, mutableListOf())
                        HomeScreenState.addBag(newBagModel)
                        bagList.value = HomeScreenState.getBagArray().toMutableList()
                        newBagName.value = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OurPackingBlue),
                border = BorderStroke(1.dp, OurPackingBlue),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Bag",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Add Bag", color = Color.White)
            }
        }
    }
}
