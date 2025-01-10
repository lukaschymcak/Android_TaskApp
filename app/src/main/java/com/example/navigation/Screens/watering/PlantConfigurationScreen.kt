package com.example.navigation.Screens.watering

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.navigation.DataStoreManager
import com.example.navigation.R
import com.example.navigation.states.HomeScreenState
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreen
import com.example.navigation.ui.theme.OurGreenLight
import com.example.navigation.ui.theme.OurRed
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

            Card(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Text(
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    text = selectedPlant!!.description,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurGreen
                )
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = OurGreen),
                ) {
                }
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxSize(),
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            text = stringResource(id = R.string.watering_frequency),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = OurBeige
                        )
                        TextField(
                            value = "",
                            onValueChange = { },
                            label = { Text(text = "${selectedPlant!!.frequency} days") },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(100.dp)
                                .background(OurGreenLight, shape = RoundedCornerShape(16.dp)),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxSize(),
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            text = stringResource(id = R.string.plant_room),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = OurBeige
                        )
                        TextField(
                            value = "",
                            onValueChange = { },
                            label = { Text(text = "set room") },
                            modifier = Modifier.padding(8.dp).width(200.dp).height(50.dp)
                                .background(OurGreenLight, shape = RoundedCornerShape(24.dp)),
                        )
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
                        onClick = { onGoBack() },
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
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = stringResource(id = R.string.delete_bag_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.delete_bag_dialog_message)) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(OurRed),
                    onClick = {
                        coroutineScope.launch {
                            dataStoreManager.deletePlant(selectedPlant!!.plantName)
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
