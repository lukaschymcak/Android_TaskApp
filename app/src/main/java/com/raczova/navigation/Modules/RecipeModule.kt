package com.raczova.navigation.modules


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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raczova.navigation.R
import com.raczova.navigation.ui.theme.OurBeige
import com.raczova.navigation.ui.theme.OurYellow


@Composable
fun RecipeModuleExample(
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = OurYellow,
        ),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                //onClick()
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
                    text = stringResource(id = R.string.recipe_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = OurBeige
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.wip),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    color = OurBeige
                )

            }
            Image(
                painter = painterResource(id = R.drawable.recipe_book),
                contentDescription = "Recipe book",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
                    .padding(16.dp)
            )
        }
    }
}