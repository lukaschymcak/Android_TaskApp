package com.example.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.modules.TripModule
import com.example.navigation.ui.theme.OurRed


@Composable
fun PackingScreen(
    onGoBack: () -> Unit, onGoToNextScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = (-120).dp)
                    .clickable { onGoBack() },
            )

            Text(
                text = "PACKING",
                color = OurRed,
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .paddingFromBaseline(top = 35.dp)
                    .offset(x = (-30).dp),
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = OurRed, thickness = 3.dp)
        Spacer(modifier = Modifier.height(24.dp))
        TripModule()


        AddTripBottomSheet()
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = {
                showBottomSheet = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
            )
            Text(text = "Add trip", fontSize = 18.sp)

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
                            value = "Enter name",
                            onValueChange = { },
                            label = { Text("Name") },
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))


                        OutlinedTextField(
                            value = "Enter arrival date",
                            onValueChange = { },
                            label = { Text("From") },
                            shape = RoundedCornerShape(16.dp)

                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = "Enter arrival date",
                            onValueChange = { },
                            label = { Text("To") },
                            shape = RoundedCornerShape(16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = {
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





