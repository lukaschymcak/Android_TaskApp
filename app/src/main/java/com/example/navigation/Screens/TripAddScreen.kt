package com.example.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurRed


@Composable
fun TripAddScreen(
    onGoBack: () -> Unit
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
                modifier = Modifier.padding(16.dp).offset(x = (-120).dp).clickable { onGoBack() },


            )

            Text(
                text = "ADD TRIP",
                color = OurRed,
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 35.dp).offset(x = (-30).dp),
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = OurRed, thickness = 3.dp)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = {
                onGoBack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                //modifier = Modifier.padding(16.dp),
            )
            Text(text = "Add trip", fontSize = 18.sp)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            OutlinedButton(
                onClick = {
                    onGoBack()
                }
            ) {
                Text(text = "Go Home", fontSize = 18.sp)
            }
        }
    }
}