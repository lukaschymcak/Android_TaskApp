package com.raczova.navigation.Modules.watering


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raczova.navigation.DataStoreManager
import com.raczova.navigation.R
import com.raczova.navigation.ui.theme.OurBeige
import com.raczova.navigation.ui.theme.OurGreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@Composable
fun WateringModuleExample(onClick: () -> Unit,
                          dataStoreManager: DataStoreManager
) {
    val coroutineScope = rememberCoroutineScope()
    var plantCount by remember { mutableIntStateOf(0) }
    var needWateringCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val plants = dataStoreManager.getPlants().first()
            plantCount = plants.size
            needWateringCount = plants.count { !it.getWatered() } 
        }
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = OurGreen,
        ),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.watering_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = OurBeige
                )
                Spacer(modifier = Modifier.height(8.dp))

                val stringPlants = if (plantCount == 0) "No plants added"
                else if (plantCount == 1) "one added plant"
                else "added plants: $plantCount"
                Text(
                    text = stringPlants,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    color = OurBeige
                )
                val howManyNeedWatering = 0
                Text(
                    text = "$howManyNeedWatering plants need watering!",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    color = OurBeige
                )
            }
            Image(
                painter = painterResource(id = R.drawable.plant_628324),
                contentDescription = "Plant",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
                    .padding(16.dp)
                    .clickable { }
            )
        }
    }
}