package com.example.mytaskapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytaskapp.ui.theme.OurBlue
import com.example.mytaskapp.ui.theme.OurYellow


@Composable
fun PillModuleExample() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(3.dp, OurBlue)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "PILLS",
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "next pill: ",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                color = Color.DarkGray
            )
            Text(
                text = "Ibalgin in 2 hours",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                color = Color.DarkGray
            )
            Text(
                text = "Pills today: 1",
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                color = Color.DarkGray
            )
        }
    }
}
