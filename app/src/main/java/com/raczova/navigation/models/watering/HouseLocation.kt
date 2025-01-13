package com.raczova.navigation.models.watering

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
    ANY_ROOM
}
