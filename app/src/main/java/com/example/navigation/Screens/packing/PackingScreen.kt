package com.example.navigation.Screens.packing

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.DataStoreManager
import com.example.navigation.R
import com.example.navigation.Screen
import com.example.navigation.models.packing.TripModel
import com.example.navigation.route
import com.example.navigation.ui.theme.OurPackingBlue
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@Composable
fun PackingScreen(
    onGoBack: () -> Unit,
    onGoToNextScreen: (String) -> Unit,
    dataStoreManager: DataStoreManager,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var tripList by remember { mutableStateOf<List<TripModel>>(emptyList()) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var tripToDelete by remember { mutableStateOf<TripModel?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            dataStoreManager.getTrips().collect { trips ->
                tripList = trips
            }
        }
    }

    fun refreshTripList() {
        coroutineScope.launch {
            dataStoreManager.getTrips().collect { trips ->
                tripList = trips
            }
        }
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { onGoBack() },
                tint = OurPackingBlue
            )
            Text(
                text = stringResource(id = R.string.packing_screen_title),
                color = OurPackingBlue,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "NOTHING",
                tint = Color.Transparent
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = OurPackingBlue),
            modifier = Modifier.padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.my_trips_title),
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (tripList.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.no_trips_message_screen),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    )
                } else {
                    tripList.forEach { trip ->
                        val packingPercentage =
                            calculatePackingPercentage(trip)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    navController.navigate("${Screen.TripScreen.route}/${trip.name}")
                                },
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = trip.name,
                                        color = OurPackingBlue,
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp),

                                        )
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(id = com.example.navigation.R.string.remove_trip),
                                        tint = OurPackingBlue,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clickable {
                                                tripToDelete = trip
                                                showDeleteDialog = true
                                            }
                                    )
                                }
                                Text(
                                    text = "(${trip.startDate} - ${trip.endDate})",
                                    color = OurPackingBlue,
                                    fontSize = 21.sp,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Text(
                                    text = stringResource(id = com.example.navigation.R.string.packing_percentage)+packingPercentage+"%",
                                    color = OurPackingBlue,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.click_to_add_bag),
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(18.dp))
        AddTripBottomSheet(
            dataStoreManager = dataStoreManager,
            onTripAdded = { refreshTripList() }
        )
    }

    if (showDeleteDialog && tripToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                tripToDelete = null
            },
            title = {
                Text(text = stringResource(id = R.string.delete_trip_title))
            },
            text = {
                Text(text = stringResource(id =R.string.delete_trip_message), fontSize = 18.sp)
            },
            confirmButton = {
                TextButton(onClick = {
                    coroutineScope.launch {
                        val updatedTrips = tripList.toMutableList()
                        updatedTrips.remove(tripToDelete)
                        dataStoreManager.saveTrips(updatedTrips)
                        tripList = updatedTrips
                        showDeleteDialog = false
                        tripToDelete = null
                    }
                }) {
                    Text(stringResource(id = R.string.delete_button), fontSize = 16.sp)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    tripToDelete = null
                }) {
                    Text(stringResource(id = com.example.navigation.R.string.cancel_button), fontSize = 16.sp)
                }
            }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripBottomSheet(
    dataStoreManager: DataStoreManager,
    onTripAdded: () -> Unit
) {
    val tripName = remember { mutableStateOf("") }
    val tripFrom = remember { mutableStateOf("") }
    val tripTo = remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val fromDatePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tripFrom.value = "$dayOfMonth.${month + 1}.$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val toDatePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tripTo.value = "$dayOfMonth.${month + 1}.$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedButton(
        onClick = { showBottomSheet = true },
        colors = ButtonDefaults.outlinedButtonColors(contentColor = OurPackingBlue),
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        Text(stringResource(id = R.string.add_trip_button), fontSize = 18.sp)

        if (showBottomSheet) {
            ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = tripName.value,
                        onValueChange = { tripName.value = it },
                        label = { Text(stringResource(id = R.string.add_trip_name_label)) }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(onClick = { fromDatePickerDialog.show() }) {
                        Text(tripFrom.value.ifEmpty { stringResource(id = R.string.select_date_from) })
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(onClick = { toDatePickerDialog.show() }) {
                        Text(tripTo.value.ifEmpty { stringResource(id = R.string.select_date_to) })
                    }

                    Button(onClick = {
                        val newTrip = TripModel(tripName.value, tripFrom.value, tripTo.value)
                        coroutineScope.launch {
                            val trips = dataStoreManager.getTrips().firstOrNull() ?: emptyList()
                            dataStoreManager.saveTrips(trips + newTrip)
                            onTripAdded()
                            showBottomSheet = false
                        }
                    }) {
                        Text(stringResource(id = R.string.add_trip_action))
                    }
                }
            }
        }
    }
}

fun calculatePackingPercentage(trip: TripModel): Int {

    val totalItems = trip.arrayBagModel.sumOf { it.itemModels.size }
    val checkedItems = trip.arrayBagModel.sumOf { bag ->
        bag.itemModels.count { it.isChecked }
    }

    return if (totalItems == 0) 100 else (checkedItems * 100) / totalItems
}

