package com.example.navigation.Screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.states.HomeScreenState
import com.example.navigation.models.Trip
import com.example.navigation.Modules.TripModule
import com.example.navigation.ui.theme.OurPackingBlue



@SuppressLint("MutableCollectionMutableState")
@Composable
fun PackingScreen(
    onGoBack: () -> Unit,
    onGoToNextScreen: () -> Unit
) {
    val tripList = remember { mutableStateListOf<Trip>().apply { addAll(HomeScreenState.getTripArray()) } }


    fun refreshTripList() {
        tripList.clear()
        tripList.addAll(HomeScreenState.getTripArray())
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent)
            .verticalScroll(scrollState),
        Arrangement.Top,
        Alignment.CenterHorizontally,
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
                text = "PACKING",
                color = OurPackingBlue,
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .paddingFromBaseline(top = 35.dp)
                    .offset(x = (-30).dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = OurPackingBlue),
            modifier = Modifier.padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "MY TRIPS:",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(20.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                color = Color.White,
                thickness = 2.dp,
                modifier = Modifier
                    .width(355.dp)
                    .offset(15.dp)
            )

            if (tripList.isEmpty()) {
                Text(
                    text = "No trips yet, add a trip :)",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                tripList.forEach { trip ->
                    Card(
                        modifier = Modifier.clickable { onGoToNextScreen() },
                        colors = CardDefaults.cardColors(
                            containerColor = OurPackingBlue,
                        ),
                    ) {
                        TripModule(
                            name = trip.getName(),
                            dateFrom = trip.getStartDate(),
                            dateTo = trip.getEndDate(),
                            percentage = 0,
                            onDelete = {
                                tripList.remove(trip)
                                HomeScreenState.removeTrip(trip)
                                refreshTripList()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))

        AddTripBottomSheet {
            refreshTripList()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripBottomSheet(onTripAdded: () -> Unit) {
    val tripName = remember { mutableStateOf("") }
    val tripFrom = remember { mutableStateOf("") }
    val tripTo = remember { mutableStateOf("") }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

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

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(contentColor = OurPackingBlue),
            border = BorderStroke(2.dp, OurPackingBlue),
            onClick = { showBottomSheet = true }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = OurPackingBlue)
            Text(text = "Add trip", fontSize = 18.sp, color = OurPackingBlue)

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "TRIP DETAILS",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        OutlinedTextField(
                            value = tripName.value,
                            onValueChange = { tripName.value = it },
                            label = { Text("Name") },
                            shape = RoundedCornerShape(16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .width(280.dp)
                                .height(55.dp)
                                .border(1.dp, SolidColor(Color.DarkGray), RoundedCornerShape(16.dp))
                                .clickable { fromDatePickerDialog.show() }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = tripFrom.value.ifEmpty { "Select Date From" },
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Start
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .width(280.dp)
                                .height(55.dp)
                                .border(1.dp, SolidColor(Color.DarkGray), RoundedCornerShape(16.dp))
                                .clickable { toDatePickerDialog.show() }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = tripTo.value.ifEmpty { "Select Date To" },
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                                textAlign = TextAlign.Start
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = {
                                HomeScreenState.addTrip(
                                    Trip(tripName.value, tripFrom.value, tripTo.value)
                                )
                                onTripAdded()
                                showBottomSheet = false
                            }
                        ) {
                            Text(text = "Add", fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}




