import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.ui.theme.OurBeige
import com.example.navigation.ui.theme.OurOrange
import com.example.navigation.ui.theme.OurPackingBlue
import com.example.navigation.ui.theme.OurYellow


@Composable
fun PackingModule(
    onClick: () -> Unit,
    tripName: String?,
    packingPercentage: Int
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = OurPackingBlue,
        ),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() }
        //border = BorderStroke(5.dp, OurPackingBlue)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "PACKING",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = OurBeige
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (tripName == null) {
                Text(
                    text ="You have no trips, click to add a trip :)",
                    fontSize = 20.sp,
                    //fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = OurBeige
                )
            } else {
                Text(
                    text = "Next trip: $tripName",
                    fontSize = 20.sp,
                    //fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = OurBeige
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Packed: $packingPercentage%",
                    fontSize = 20.sp,
                    //fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = OurBeige
                )
            }
        }
    }
}
