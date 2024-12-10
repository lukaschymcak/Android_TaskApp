package com.example.navigation.Screens.watering

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.models.watering.PlantModel
import com.example.navigation.ui.theme.OurGreen

@Composable
fun WateringScreen(onGoBack: () -> Unit,
                   onGoToAddPlant: () -> Unit,
                   addedPlants: List<PlantModel>
){
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
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OurGreen
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Nothing",
                tint = Color.Transparent
            )


        }
        if (addedPlants.isNotEmpty()) {
            Text(
                text = "Added Plants:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = OurGreen,
                modifier = Modifier.padding(8.dp)
            )
            addedPlants.forEach { plant ->
                Text(
                    text = plant.plantName,
                    fontSize = 16.sp,
                    color = OurGreen,
                    modifier = Modifier.padding(4.dp)
                )
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