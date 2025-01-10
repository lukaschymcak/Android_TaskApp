package com.example.navigation.Screens.onboardingViewWatering

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurWateringGreen

@Composable
fun WateringButtonUI(
    text: String = "Next",
    backgroundColor: Color = OurBeige,
    textColor: Color = OurWateringGreen,
    textStyle: TextStyle  = MaterialTheme.typography.titleMedium,
    fontSize : Int = 14,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor, textColor
        ), shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle
        )
    }

}


@Preview
@Composable
fun NextButton(){
    WateringButtonUI (text = "Next"){  }
}

@Preview
@Composable
fun BackButton(){
    WateringButtonUI (
        text = "Back",
        backgroundColor = Color.Transparent,
        textColor = Color.Gray,
        textStyle = MaterialTheme.typography.bodySmall,
        fontSize = 13
    ){  }
}