package com.raczova.navigation.models.watering

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.raczova.navigation.R
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class PlantModel(
    val id: String = UUID.randomUUID().toString(),
    val plantName: String,
    val description: String = "",
    val location: HouseLocation = HouseLocation.ROOM,
    val frequency: Int = 7,
    val water: String = "200ml",
    val image: Int,
    private var watered: Boolean,
    var lastWatered: Long = 0L
) {
    fun getWatered(): Boolean {
        val currentTime = System.currentTimeMillis()
        if (watered && currentTime - lastWatered >= frequency * 24 * 60 * 60 * 1000) {
            watered = false
        }
        return watered
    }

    fun setWatered(value: Boolean) {
        watered = value
    }
}


object PresetPlants {

    private var monstera = PlantModel(
        plantName = "Monstera",
        description = "Monstera (Monstera deliciosa) is a popular tropical houseplant known for its large, glossy, heart-shaped leaves with unique holes and splits. It thrives in bright, indirect light and is low-maintenance.",
        frequency = 3,
        water = "300ml",
        image = R.drawable.monstera,
        watered = false
    )

    private var orchid = PlantModel(
        plantName = "Orchid",
        description = "Orchids (Phalaenopsis) are elegant tropical plants known for their stunning, long-lasting flowers. They thrive in bright, indirect light and require careful watering and humidity.",
        frequency = 7,
        water = "100ml",
        image = R.drawable.orchid,
        watered = false
    )

    private var zzPlant = PlantModel(
        plantName = "ZZ Plant",
        description = "The ZZ plant (Zamioculcas zamiifolia) is a hardy, low-maintenance indoor plant known for its glossy, dark green leaves.",
        frequency = 14,
        water = "250ml",
        image = R.drawable.zz_plant,
        watered = false
    )

    private var snakePlant = PlantModel(
        plantName = "Snake Plant",
        description = "The Snake Plant is a resilient and low-maintenance plant with tall, sword-like leaves featuring green and yellow variegation.",
        frequency = 21,
        water = "300ml",
        image = R.drawable.snake_plant,
        watered = false
    )

    private var spiderPlant = PlantModel(
        plantName = "Spider Plant",
        description = "The Spider Plant is a hardy and adaptable houseplant with long, arching, variegated leaves.",
        frequency = 7,
        water = "300ml",
        image = R.drawable.spider_plant,
        watered = false
    )

    private var aloeVera = PlantModel(
        plantName = "Aloe Vera",
        description = "Aloe Vera is a versatile succulent with thick, fleshy leaves filled with gel known for its medicinal and skincare benefits.",
        frequency = 14,
        water = "200ml",
        image = R.drawable.aloe_vera,
        watered = false
    )

    private var africanViolet = PlantModel(
        plantName = "African Violet",
        description = "The African Violet is a compact houseplant with fuzzy leaves and vibrant flowers in purple, pink, or white.",
        frequency = 7,
        water = "200ml",
        image = R.drawable.african_violet,
        watered = false
    )

    val presetPlants = listOf(
        monstera,
        orchid,
        zzPlant,
        snakePlant,
        spiderPlant,
        aloeVera,
        africanViolet
    )
    var selectedPlants = mutableListOf<PlantModel>()
}
@Composable
fun getLocalizedPlantName(plantName: String): String {
    return when (plantName) {
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

