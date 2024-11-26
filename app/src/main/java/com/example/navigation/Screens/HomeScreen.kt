import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.navigation.Modules.GymModuleExample
import com.example.navigation.Modules.WateringModuleExample
import com.example.navigation.models.Packing
import com.example.navigation.ui.theme.OurBlue
import com.example.navigation.ui.theme.OurGreen
import com.example.navigation.ui.theme.OurPackingBlue
import com.example.navigation.ui.theme.OurYellow


@Composable
fun HomeScreen(
    onGoToNextScreen: () -> Unit
) {
    val context = LocalContext.current
    val name by remember { mutableStateOf(HomeScreenState.getName(context)) }
    var packingModules by remember { mutableStateOf(listOf<Packing>()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Hello, $name",
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 35.dp).offset(x = (25).dp),
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(16.dp)
                    .offset(x = 90.dp)
            )
        }

        Text(
            text = "Tap any module for details",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(24.dp))

        PackingModule(
            onClick = onGoToNextScreen
        )
        WateringModuleExample()
        GymModuleExample()

        Spacer(modifier = Modifier.height(24.dp))

        AddNewModuleHalfScreen(
            onAddPackingModule = {
                if (packingModules.isEmpty()) {
                    val mockTrip = Packing(
                        color = "OurPackingBlue",
                        percentage = 70,
                        arrayTrip = listOf()
                    )
                    packingModules = listOf(mockTrip)
                }
            }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewModuleHalfScreen(onAddPackingModule: () -> Unit) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = {
                showBottomSheet = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
            )
            Text(text = "Add module", fontSize = 18.sp)

            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            "CHOOSE A MODULE",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        ModuleOption("PACKING MODULE", OurPackingBlue, onAddPackingModule)
                        ModuleOption("WATERING REMINDER", OurGreen)
                        ModuleOption("GYM TRACKER", OurYellow)
                        ModuleOption("PILL REMINDER", OurBlue)

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ModuleOption(moduleName: String, color: Color, onAddPackingModule: () -> Unit) {
    OutlinedButton(
        onClick = {
            if (moduleName == "PACKING MODULE") {
                onAddPackingModule()
            }
        },
        modifier = Modifier
            .padding(8.dp)
            .border(2.dp, color, RoundedCornerShape(25.dp))
    ) {
        Text(
            moduleName,
            fontSize = 20.sp,
            color = color,
        )
    }
}

@Composable
fun ModuleOption(moduleName: String, color: Color) {
    Text(
        moduleName,
        modifier = Modifier
            .padding(8.dp)
            .border(2.dp, color, RoundedCornerShape(25.dp))
            .padding(16.dp),
        fontSize = 20.sp,
        color = color,
    )
}