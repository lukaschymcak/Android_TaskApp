
package com.raczova.navigation.Screens.watering

import com.raczova.navigation.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raczova.navigation.DataStoreManager
import com.raczova.navigation.models.watering.getLocalizedPlantName
import com.raczova.navigation.models.watering.getLocalizedPlantRoom
import com.raczova.navigation.states.HomeScreenState
import com.raczova.navigation.ui.theme.OurBeige
import com.raczova.navigation.ui.theme.OurGreen
import kotlinx.coroutines.launch

@Composable
fun WateringScreen(
    onGoBack: () -> Unit,
    onGoToAddPlant: () -> Unit,
    onGoToPlantConfiguration: () -> Unit,
    dataStoreManager: DataStoreManager
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val plantsFlow = remember { dataStoreManager.getPlants() }
    val plantsState = plantsFlow.collectAsState(initial = emptyList())

    var selectedIndex by remember { mutableIntStateOf(0) }

    val plantCounts = plantsState.value.groupingBy { it.location }.eachCount()

    val sortedLocations = plantCounts.keys.sortedByDescending { plantCounts[it] }
    val tabs = listOf("${getLocalizedPlantRoom("ALL")} (${plantsState.value.size})") +
            sortedLocations.map { location ->
                "${getLocalizedPlantRoom(location.name)} (${plantCounts[location]})"
            }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_to_home),
                modifier = Modifier.clickable { onGoBack() },
                tint = OurGreen
            )
            Text(
                text = stringResource(R.string.added_plants),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = OurGreen
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.Transparent
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            tabs.forEachIndexed { index, label ->
                FilterChip(
                    modifier = Modifier.padding(8.dp).height(40.dp),
                    onClick = { selectedIndex = index },
                    label = { Text(label) },
                    selected = selectedIndex == index
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val filteredPlants = when (selectedIndex) {
                0 -> plantsState.value
                else -> {
                    val location = sortedLocations[selectedIndex - 1]
                    plantsState.value.filter { it.location == location }
                }
            }

            if (filteredPlants.isNotEmpty()) {
                items(filteredPlants) { plant ->
                    var isWatered by remember { mutableStateOf(plant.getWatered()) }

                    Card(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .clickable {
                                HomeScreenState.setSelectedPlant(context, plant)
                                onGoToPlantConfiguration()
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = OurGreen)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (isWatered) R.drawable.watered else R.drawable.waterdrop
                                ),
                                contentDescription = "Watering Status",
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(70.dp)
                                    .padding(8.dp)
                                    .clickable {
                                        isWatered = !isWatered
                                        plant.setWatered(isWatered)
                                        plant.lastWatered = if (isWatered) System.currentTimeMillis() else 0L
                                        coroutineScope.launch {
                                            dataStoreManager.savePlants(plantsState.value)
                                        }
                                    }
                                    .border(
                                        width = 2.dp,
                                        color = if (isWatered) OurBeige else OurGreen,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .padding(8.dp)
                            )
                            Column {
                                val location = plant.location.toString()
                                    .replace("_", " ").lowercase()
                                    .replaceFirstChar { it.uppercase() }

                                Text(
                                    text = location,
                                    fontSize = 20.sp,
                                    color = OurBeige,
                                    modifier = Modifier.padding(8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = getLocalizedPlantName(plant.plantName),
                                    fontSize = 30.sp,
                                    color = OurBeige,
                                    modifier = Modifier.padding(8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                val needsWater = if (isWatered) "(water every ${plant.frequency} days)" else "Needs watering!"
                                Text(
                                    text = needsWater,
                                    fontSize = 16.sp,
                                    color = OurBeige,
                                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 12.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Image(
                                painter = painterResource(id = plant.image),
                                contentDescription = "Plant image",
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            } else {
                item {
                    Text(
                        text = stringResource(R.string.no_plants_added),
                        fontSize = 16.sp,
                        color = OurGreen,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

            item {
                OutlinedButton(
                    onClick = { onGoToAddPlant() },
                    modifier = Modifier.padding(16.dp),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                        contentColor = OurGreen
                    )
                ) {
                    Text(text = stringResource(R.string.add_plant_title))
                }
            }
        }
    }
}