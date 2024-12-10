package com.example.navigation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.R
import com.example.navigation.ui.theme.OurGreen
import com.example.navigation.models.watering.PlantModel
import com.example.navigation.models.watering.PresetPlants.presetPlants
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreenLight
import com.example.navigation.ui.theme.OurGrey
import com.example.navigation.ui.theme.OurPackingBlue
import com.example.navigation.ui.theme.OurWateringGreen


@SuppressLint("DiscouragedApi")
@Composable
fun PlantAddScreen(onGoBack: () -> Unit) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back to watering",
                modifier = Modifier.clickable { onGoBack() },
                tint = OurGreen
            )
            Text(
                text = "ADD PLANT",
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
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Select from preset plants or add a custom one!",
                fontSize = 15.sp,
                color = OurGreen
            )
        }

        items(presetPlants) { plant ->
            val cardColor = if (plant.plantName == "Custom") OurGreenLight else OurGreen
            val cardTextColor = if (plant.plantName == "Custom") OurBeige else OurBeige


            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor
                )
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    val resourceId = remember(plant.image) {
                        context.resources.getIdentifier(plant.image, "drawable", context.packageName)
                    }
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "Plant image",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(8.dp)
                    )
                    Text(
                        text = plant.plantName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = cardTextColor
                    )
                }

            }
        }
    }
}