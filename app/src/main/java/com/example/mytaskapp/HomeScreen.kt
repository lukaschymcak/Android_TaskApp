package com.example.mytaskapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                Arrangement.Top,
                Alignment.CenterHorizontally
    ) {
        Text(
            text = "hello, lukas",

            fontSize = 25.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.paddingFromBaseline(top = 16.dp)
        )
        Text(
            text = "tap any module for details",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        PackingModuleExample()
        WateringModuleExample()
        PillModuleExample()
        GymModuleExample()
    }

}


