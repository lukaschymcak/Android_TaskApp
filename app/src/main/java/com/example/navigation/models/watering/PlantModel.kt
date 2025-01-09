package com.example.navigation.models.watering

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.navigation.R
import kotlinx.serialization.Serializable

@Serializable
data class PlantModel(
    val plantName: String,
    val description: String = "",
    val location: HouseLocation = HouseLocation.LIVING_ROOM,
    val frequency: Int = 7,
    val water: String = "200ml",
    val image: String,
    private var watered: Boolean
) {
    fun getWatered(): Boolean {
        return watered
    }

    fun setWatered(value: Boolean) {
        watered = value
    }
}


object PresetPlants {
    private var custom = PlantModel(
        plantName = "Custom",
        image = "edit_pencil",
        watered = false
    )

    private var monstera = PlantModel(
        plantName = "Monstera",
        description = "Monstera (Monstera deliciosa) is a popular tropical houseplant known for its large, glossy, heart-shaped leaves with unique holes and splits. It thrives in bright, indirect light and is low-maintenance.",
        frequency = 3,
        water = "300ml",
        image = "monstera",
        watered = false
    )

    private var orchid = PlantModel(
        plantName = "Orchid",
        description = "Orchids (Phalaenopsis) are elegant tropical plants known for their stunning, long-lasting flowers. They thrive in bright, indirect light and require careful watering and humidity.",
        frequency = 7,
        water = "100ml",
        image = "orchid",
        watered = false
    )

    private var zzPlant = PlantModel(
        plantName = "ZZ Plant",
        description = "The ZZ plant (Zamioculcas zamiifolia) is a hardy, low-maintenance indoor plant known for its glossy, dark green leaves.",
        frequency = 14,
        water = "250ml",
        image = "zz_plant",
        watered = false
    )

    private var snakePlant = PlantModel(
        plantName = "Snake Plant",
        description = "The Snake Plant is a resilient and low-maintenance plant with tall, sword-like leaves featuring green and yellow variegation.",
        frequency = 21,
        water = "300ml",
        image = "snake_plant",
        watered = false
    )

    private var spiderPlant = PlantModel(
        plantName = "Spider Plant",
        description = "The Spider Plant is a hardy and adaptable houseplant with long, arching, variegated leaves.",
        frequency = 7,
        water = "300ml",
        image = "spider_plant",
        watered = false
    )

    private var aloeVera = PlantModel(
        plantName = "Aloe Vera",
        description = "Aloe Vera is a versatile succulent with thick, fleshy leaves filled with gel known for its medicinal and skincare benefits.",
        frequency = 14,
        water = "200ml",
        image = "aloe_vera",
        watered = false
    )

    private var africanViolet = PlantModel(
        plantName = "African Violet",
        description = "The African Violet is a compact houseplant with fuzzy leaves and vibrant flowers in purple, pink, or white.",
        frequency = 7,
        water = "200ml",
        image = "african_violet",
        watered = false
    )

    val presetPlants = listOf(
        custom,
        monstera,
        orchid,
        zzPlant,
        snakePlant,
        spiderPlant,
        aloeVera,
        africanViolet
    )
}
@Composable
fun getLocalizedPlantName(plantName: String): String {
    return when (plantName) {
        "Custom" -> stringResource(id = R.string.custom)
        "Monstera" -> stringResource(id = R.string.monstera)
        "Orchid" -> stringResource(id = R.string.orchid)
        "ZZ Plant" -> stringResource(id = R.string.zz_plant)
        "Snake Plant" -> stringResource(id = R.string.snake_plant)
        "Spider Plant" -> stringResource(id = R.string.spider_plant)
        "Aloe Vera" -> stringResource(id = R.string.aloe_vera)
        "African Violet" -> stringResource(id = R.string.african_violet)
        else -> plantName
    }
}

@Composable
fun getLocalizedPlantDescription(plantName: String): String {
    return when (plantName) {
        "Custom" -> stringResource(id = R.string.custom_description)
        "Monstera" -> stringResource(id = R.string.monstera_description)
        "Orchid" -> stringResource(id = R.string.orchid_description)
        "ZZ Plant" -> stringResource(id = R.string.zz_plant_description)
        "Snake Plant" -> stringResource(id = R.string.snake_plant_description)
        "Spider Plant" -> stringResource(id = R.string.spider_plant_description)
        "Aloe Vera" -> stringResource(id = R.string.aloe_vera_description)
        "African Violet" -> stringResource(id = R.string.african_violet_description)
        else -> ""
    }
}

