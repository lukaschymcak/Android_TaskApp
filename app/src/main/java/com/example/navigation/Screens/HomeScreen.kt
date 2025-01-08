import android.provider.Settings.Global.getString
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.states.HomeScreenState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.navigation.DataStoreManager
import com.example.navigation.Modules.packing.PackingModule
import com.example.navigation.Modules.ShoppingModuleExample
import com.example.navigation.Modules.RecipeModuleExample
import com.example.navigation.Modules.watering.WateringModuleExample
import com.example.navigation.R
import com.example.navigation.models.packing.TripModel
import kotlinx.coroutines.flow.firstOrNull


@Composable
fun HomeScreen(
    onGoToNextScreen: () -> Unit,
    dataStoreManager: DataStoreManager,
    onGoToSettings: () -> Unit,
    onGoToWatering: () -> Unit,
    onGoToShopping: () -> Unit,
    onGoToRecipe: () -> Unit
) {
    val context = LocalContext.current
    val name by remember { mutableStateOf(HomeScreenState.getName(context)) }
    var closestTrip by remember { mutableStateOf<TripModel?>(null) }
    var packingPercentage by remember { mutableIntStateOf(100) }

    LaunchedEffect(Unit) {
        val trips = dataStoreManager.getTrips().firstOrNull() ?: emptyList()
        closestTrip = trips.minByOrNull { it.startDate }
        packingPercentage = calculatePackingPercentage(closestTrip)
    }

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
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(4.dp)
                    .clickable {
                        onGoToSettings()
                    }

            )

            Text(
                text = stringResource(id = R.string.hello_name) + " " +name+"!",
                fontSize = 32.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .paddingFromBaseline(top = 35.dp)
                    .offset(x = (0).dp),
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Nothing",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))


        PackingModule(
            onClick = onGoToNextScreen,
            tripName = closestTrip?.name,
            packingPercentage = packingPercentage
        )
        Spacer(modifier = Modifier.height(8.dp))
        WateringModuleExample(onClick = onGoToWatering)
        Spacer(modifier = Modifier.height(8.dp))
        ShoppingModuleExample(onClick = onGoToShopping)
        Spacer(modifier = Modifier.height(8.dp))
        RecipeModuleExample(onClick = onGoToRecipe)


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.tap_module),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.created_by),
            fontSize = 13.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )
    }
    }
}


fun calculatePackingPercentage(trip: TripModel?): Int {
    if (trip == null) return 0
    val totalItems = trip.arrayBagModel.sumOf { it.itemModels.size }
    val checkedItems = trip.arrayBagModel.sumOf { bag ->
        bag.itemModels.count { it.isChecked }
    }

    return if (totalItems == 0) 0 else (checkedItems * 100) / totalItems
}

