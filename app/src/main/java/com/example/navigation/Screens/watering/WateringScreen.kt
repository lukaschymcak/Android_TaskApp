package com.example.navigation.Screens.watering

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.DataStoreManager
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreen

@SuppressLint("DiscouragedApi")
@Composable
fun WateringScreen(
    onGoBack: () -> Unit,
    onGoToAddPlant: () -> Unit,
    dataStoreManager: DataStoreManager
) {
    val coroutineScope = rememberCoroutineScope()
    val plantsFlow = remember { dataStoreManager.getPlants() }
    val plantsState = plantsFlow.collectAsState(initial = emptyList())
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back home",
                modifier = Modifier.clickable { onGoBack() },
                tint = OurGreen
            )
            Text(
                text = "WATERING",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = OurGreen
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Nothing",
                tint = Color.Transparent
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        if (plantsState.value.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = OurGreen
                )
            ) {
                Column (
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Added Plants:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = OurBeige,
                        modifier = Modifier.padding(16.dp)
                    )
                    plantsState.value.forEach { plant ->
                        Card(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = OurBeige)
                        ) {
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                            ){
                                val resourceId = remember(plant.image) {
                                    context.resources.getIdentifier(plant.image, "drawable", context.packageName)
                                }
                                Image(
                                    painter = painterResource(id = resourceId),
                                    contentDescription = "Plant image",
                                    modifier = Modifier
                                        .width(90.dp)
                                        .height(90.dp)
                                        .padding(16.dp)
                                )
                                Column {
                                    val location = plant.location.toString().replace("_"," ").lowercase().capitalize()
                                    Text(
                                        text = location,
                                        fontSize = 20.sp,
                                        color = OurGreen,
                                        modifier = Modifier.padding(8.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = plant.plantName,
                                        fontSize = 30.sp,
                                        color = OurGreen,
                                        modifier = Modifier.padding(8.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }
                        }

                    }
                }
            }
            } else {
                Text(
                    text = "No plants added.",
                    fontSize = 16.sp,
                    color = OurGreen,
                    modifier = Modifier.padding(4.dp)
                )
            }

        OutlinedButton(
            onClick = { onGoToAddPlant() },
            modifier = Modifier.padding(16.dp),
            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                contentColor = OurGreen
            )
        ) {
            Text(text = "Add plant")
        }
    }
}

