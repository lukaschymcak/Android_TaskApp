package com.example.navigation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.navigation.HomeScreenState
import com.example.navigation.Modules.*
import androidx.compose.ui.platform.LocalContext



@Composable
fun AddModule() {
    var modules = mutableListOf(PillModuleExample(), PackingModuleExample(), GymModuleExample(), WateringModuleExample())
    modules.remove(PillModuleExample())}



@Composable
fun HomeScreen(
    onGoToNextScreen: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(HomeScreenState.getName(context)) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(16.dp).offset(x = (-70).dp),
            )

            Text(
                text = "Hello, $name",
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 35.dp)
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier.padding(16.dp).offset(x = 70.dp),
            )
        }

        Text(
            text = "Tap any module for details",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )

        Button(
            onClick = {
                onGoToNextScreen()
                HomeScreenState.setWasShown(true)
            }
        ) {
            Text(text = "Packing", fontSize = 18.sp)
        }

        AddModule()
    }
}