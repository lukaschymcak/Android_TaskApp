package com.example.navigation.Screens.watering

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.R
import com.example.navigation.ui.theme.OurGreen

@Composable
fun PlantConfigurationScreen(onGoBack: () -> Unit,
                             onGoToAddPlant: () -> Unit)
{
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_to_watering),
                    modifier = Modifier.clickable { onGoToAddPlant() },
                    tint = OurGreen
                )
                Text(
                    text = "Monstera - need to edit",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurGreen
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Nothing",
                    tint = Color.Transparent
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                text = "this is a plant configuration screen",
                fontSize = 15.sp,
                color = OurGreen
            )
        }

}}