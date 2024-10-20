package com.example.mytaskapp

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {

    Column (

        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally

    ){
        //row to align image to the top

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
            value = "Enter your name",

            onValueChange = { },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(15.dp),
        )

        Spacer(modifier = Modifier.height(128.dp))

        Button(onClick = (
                { }
                ), modifier = Modifier
            .padding(10.dp)
            .width(150.dp)
            .height(55.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF5AB54)) ) {
            Text("Enter", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
    }
}