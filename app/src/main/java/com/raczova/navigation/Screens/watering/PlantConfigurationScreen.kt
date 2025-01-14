package com.raczova.navigation.Screens.watering

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.raczova.navigation.R
import com.raczova.navigation.models.watering.HouseLocation
import com.raczova.navigation.states.HomeScreenState
import com.raczova.navigation.ui.theme.OurBeige
import com.raczova.navigation.ui.theme.OurGreen
import com.raczova.navigation.ui.theme.OurGreenLight
import com.raczova.navigation.ui.theme.OurRed
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlantConfigurationScreen(
    onGoBack: () -> Unit,
    dataStoreManager: DataStoreManager
) {
    val context = LocalContext.current
    val selectedPlant by remember { mutableStateOf(HomeScreenState.getSelectedPlant(context)) }
    val coroutineScope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var updatedFrequency by remember { mutableStateOf(selectedPlant?.frequency.toString()) }
    var expanded by remember { mutableStateOf(false) }
    var updatedLocation by remember { mutableStateOf(selectedPlant?.location ?: HouseLocation.ROOM) }

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
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = selectedPlant!!.plantName.uppercase(Locale.getDefault()),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurGreen
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = selectedPlant!!.image),
                contentDescription = "plant image",
                modifier = Modifier
                    .padding(8.dp)
                    .width(150.dp)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = selectedPlant!!.description,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OurGreen,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = OurGreen),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "recommended watering amount = "+selectedPlant!!.water,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = OurBeige
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = OurGreen),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = R.string.watering_frequency)+" (days)",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = OurBeige
                    )
                    TextField(
                        value = updatedFrequency,
                        onValueChange = { updatedFrequency = it },
                        label = { Text(text = "edit") },
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                            .height(40.dp)
                            .background(OurGreenLight, shape = RoundedCornerShape(16.dp))
                    )
//                    NumberPicker(
//                        value = updatedFrequency.toInt(),
//                        range = 1..30,
//                        onValueChange = { updatedFrequency = it.toString() },
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .background(OurGreenLight, shape = RoundedCornerShape(16.dp))
//                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = OurGreen),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = R.string.plant_room),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = OurBeige
                    )
                    Box(modifier = Modifier.padding(8.dp)) {
                        Button(
                            onClick = { expanded = true },
                            colors = ButtonDefaults.buttonColors(OurGreenLight),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = updatedLocation.name.replace("_", " ").lowercase()
                                    .replaceFirstChar { it.uppercase() },
                                color = OurBeige
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            HouseLocation.entries.forEach { location ->
                                DropdownMenuItem(
                                    onClick = {
                                        updatedLocation = location
                                        expanded = false
                                    },
                                    text = {
                                        Text(
                                            text = location.name.replace("_", " ").lowercase()
                                                .replaceFirstChar { it.uppercase() },
                                            color = OurGreen
                                        )
                                    }
                                )
                            }
                        }
                    }


                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = { onGoBack() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel_button),
                        fontSize = 20.sp,
                        color = OurGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(OurGreen),
                    onClick = {
                        coroutineScope.launch {
                            selectedPlant?.let {
                                val updatedPlant = it.copy(
                                    frequency = updatedFrequency.toIntOrNull() ?: it.frequency,
                                    location = updatedLocation
                                )
                                dataStoreManager.savePlants(
                                    dataStoreManager.getPlants().first().map { plant ->
                                        if (plant.plantName == updatedPlant.plantName) updatedPlant else plant
                                    }
                                )
                                onGoBack()
                            }
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.save_button),
                        fontSize = 20.sp,
                        color = OurBeige,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(OurRed),
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_plant_button),
                        fontSize = 20.sp,
                        color = OurBeige,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = stringResource(id = R.string.delete_plant_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.delete_plant_dialog_message)) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(OurRed),
                    onClick = {
                        coroutineScope.launch {
                            dataStoreManager.deletePlant(selectedPlant!!.id)
                            showDeleteDialog = false
                            onGoBack()
                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_button),
                        color = OurBeige
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel_button),
                        color = OurGreen
                    )
                }
            }
        )
    }
}
