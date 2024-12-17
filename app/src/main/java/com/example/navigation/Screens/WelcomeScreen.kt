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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.states.HomeScreenState
import com.example.navigation.R
import com.example.navigation.states.PreferencesHelper

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onGoToNextScreen: () -> Unit) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(PreferencesHelper.getName(context) ?: "") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logpng),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(50.dp)
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally),
        )

        Text(
            text = stringResource(R.string.welcome_screen_title),
            fontSize = 55.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = name,
            onValueChange = {
                name = it
                HomeScreenState.setName(context, it)
                PreferencesHelper.setName(context, it)
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(15.dp),
        )

        Spacer(modifier = Modifier.height(128.dp))
        Button(
            onClick = {
                PreferencesHelper.setWelcomeScreenShown(context, true)
                onGoToNextScreen()
            }
        ) {
            Text(
                stringResource(R.string.enter),
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}