package com.example.mytaskapp.Screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytaskapp.GymModuleExample
import com.example.mytaskapp.Modules.PackingModuleExample
import com.example.mytaskapp.Modules.PillModuleExample
import com.example.mytaskapp.Modules.WateringModuleExample


@Composable
fun AddModule() {
    var modules = mutableListOf(PillModuleExample(), PackingModuleExample(), GymModuleExample(), WateringModuleExample())


}





@Composable
fun HomeScreen(
    onGoToNextScreen: () -> Unit
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
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(16.dp).offset(x = (-70).dp),
            )

            Text(
                text = "hello, lukas",
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
        Button(onClick = {onGoToNextScreen()
        }) {
            Text(text = "Packing")
        }

        Text(
            text = "tap any module for details",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )

        AddModule()



    }


}


