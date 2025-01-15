package com.raczova.navigation.models.watering

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.raczova.navigation.R
import kotlinx.serialization.Serializable

@Serializable
enum class HouseLocation {
    KIDS_ROOM,
    LIVING_ROOM,
    BEDROOM,
    BATHROOM,
    DINING_ROOM,
    KITCHEN,
    OUTDOORS,
    ROOM
}
@Composable
fun getLocalizedPlantRoom(plantRoom: String): String {
    return when (plantRoom) {
        "KIDS_ROOM" -> stringResource(id = R.string.kids_room)
        "LIVING_ROOM" -> stringResource(id = R.string.living_room)
        "BEDROOM" -> stringResource(id = R.string.bedroom)
        "BATHROOM" -> stringResource(id = R.string.bathroom)
        "DINING_ROOM" -> stringResource(id = R.string.dining_room)
        "KITCHEN" -> stringResource(id = R.string.kitchen)
        "OUTDOORS" -> stringResource(id = R.string.outdoors)
        "ROOM" -> stringResource(id = R.string.room)
        "ALL" -> stringResource(id = R.string.allroom)
        else -> plantRoom
    }
}
