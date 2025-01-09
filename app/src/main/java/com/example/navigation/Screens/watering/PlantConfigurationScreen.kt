package com.example.navigation.Screens.watering

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.DataStoreManager
import com.example.navigation.R
import com.example.navigation.models.watering.PlantModel
import com.example.navigation.models.watering.getLocalizedPlantDescription
import com.example.navigation.models.watering.getLocalizedPlantName
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurGreen

@Composable
fun PlantConfigurationScreen(onGoBack: () -> Unit,
                             onGoToAddPlant: () -> Unit,
                             plantName: String)
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
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = plantName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurGreen
                )
            }
            Image(
                painter = painterResource(id = R.drawable.monstera),
                contentDescription = getLocalizedPlantName(plantName),
                modifier = Modifier
                    .padding(8.dp)
                    .width(150.dp)
                    .height(150.dp)
            )

            Card(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .padding(0.dp),
                shape = RoundedCornerShape(0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                ) {
                Text(
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    text = getLocalizedPlantDescription(plantName),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurGreen
                )

                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = OurGreen),
                ) {
                    Row {

                        Text(
                            modifier = Modifier.padding(8.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            text = stringResource(id = R.string.first_day),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = OurBeige
                        )

                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = OurGreen),
                ) {
                    Row {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            text = stringResource(id = R.string.watering_frequency),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = OurBeige
                        )
                        OutlinedTextField(
                            value = "set frequency",
                            onValueChange = { },
                            label = { Text(text = "14 days") },
                            modifier = Modifier.padding(8.dp)
                        )

                    }

                }
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = OurGreen),
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        text = stringResource(id = R.string.plant_room),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = OurBeige
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                OutlinedButton(
                    //colors = ButtonDefaults.buttonColors(OurGreen),
                    onClick = { onGoBack() },
                    modifier = Modifier.padding(8.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.cancel_button),
                        fontSize = 20.sp,
                        color = OurGreen,
                        fontWeight = FontWeight.Bold
                    )
            }
                Button(
                    colors = ButtonDefaults.buttonColors(OurGreen),
                    onClick = { onGoToAddPlant() },
                    modifier = Modifier.padding(8.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.save_button),
                        fontSize = 20.sp,
                        color = OurBeige,
                        fontWeight = FontWeight.Bold
                    )
            }
            }


        }

}}