package com.example.navigation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.HomeScreenState
import com.example.navigation.R



@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onGoToNextScreen: () -> Unit) {
    var name by remember { mutableStateOf(HomeScreenState.getName()) }
    Column (

        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally

    ){

        Image(
            painter = painterResource(id = R.drawable.logojpg),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(50.dp)
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally),

            )

        Text(
            text = "Welcome!",

            fontSize = 55.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)

        )
        TextField(

            value = name,

            onValueChange = { newName -> name = newName },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(15.dp),
        )

        Spacer(modifier = Modifier.height(128.dp))
        Button(
            onClick = {
                onGoToNextScreen()
            }
        , modifier = Modifier
            .padding(10.dp)
            .width(150.dp)
            .height(55.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF5AB54)) ) {
            Text("Enter", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
    }
}