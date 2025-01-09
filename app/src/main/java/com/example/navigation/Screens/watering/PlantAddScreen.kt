package com.example.navigation.Screens.watering

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.DataStoreManager
import com.example.navigation.R
import com.example.navigation.models.watering.PlantModel
import com.example.navigation.ui.theme.OurGreen
import com.example.navigation.models.watering.PresetPlants.presetPlants
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreenLight
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


@SuppressLint("DiscouragedApi")
@Composable
fun PlantAddScreen(onGoToWatering: () -> Unit,
                   onGoToPlantConfiguration: (String) -> Unit,
                   onPlantAdded: (PlantModel) -> Unit,
                   dataStoreManager: DataStoreManager
){
    val coroutineScope = rememberCoroutineScope()
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
                contentDescription = stringResource(id = R.string.back_to_watering),
                modifier = Modifier.clickable { onGoToWatering() },
                tint = OurGreen
            )
            Text(
                text = stringResource(id = R.string.add_plant),
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
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                text = stringResource(id = R.string.select_from_preset_or_add_custom),
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
                    .fillMaxWidth()
                    .clickable {
                        onGoToPlantConfiguration(plant.plantName)
//                        coroutineScope.launch {
//                            try {
//                                val currentPlants = dataStoreManager.getPlants()
//                                    .firstOrNull()
//                                    ?.toMutableList() ?: mutableListOf()
//                                currentPlants.add(plant)
//
//                                dataStoreManager.savePlants(currentPlants)
//                                onGoToPlantConfiguration()
//                            } catch (e: Exception) {
//                                e.printStackTrace()
//                            }
//                        }
                    },
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

