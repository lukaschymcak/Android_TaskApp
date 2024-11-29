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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.DataStoreManager
import com.example.navigation.Screen
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { onGoBack() },
                tint = OurPackingBlue
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "PACKING",
                color = OurPackingBlue,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
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
                    text = "MY TRIPS:",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (tripList.isEmpty()) {
                    Text(
                        text = "No trips yet, add a trip :)",
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
                                        contentDescription = "Remove",
                                        tint = OurPackingBlue,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clickable {
                                                coroutineScope.launch {
                                                    val updatedTrips = tripList.toMutableList()
                                                    updatedTrips.remove(trip)
                                                    dataStoreManager.saveTrips(updatedTrips)
                                                    tripList = updatedTrips
                                                }
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
                                    text = "Packing: $packingPercentage%",
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

        AddTripBottomSheet(
            dataStoreManager = dataStoreManager,
            onTripAdded = { refreshTripList() }
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
        Text("Add trip", fontSize = 18.sp)

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
                        label = { Text("Name") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(onClick = { fromDatePickerDialog.show() }) {
                        Text(tripFrom.value.ifEmpty { "Select Date From" })
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(onClick = { toDatePickerDialog.show() }) {
                        Text(tripTo.value.ifEmpty { "Select Date To" })
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
                        Text("Add Trip")
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

    return if (totalItems == 0) 0 else (checkedItems * 100) / totalItems
}

