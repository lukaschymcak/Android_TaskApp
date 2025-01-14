package com.raczova.navigation.models.watering


import androidx.compose.ui.test.junit4.createComposeRule
import com.raczova.navigation.R
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PlantModelTest {

    @Test
    fun testGetWatered() {
        val plant = PlantModel(
            plantName = "Test Plant",
            image = R.drawable.monstera,
            watered = true
        )
        assertEquals(true, plant.getWatered())
    }

    @Test
    fun testSetWatered() {
        val plant = PlantModel(
            plantName = "Test Plant",
            image = R.drawable.monstera,
            watered = false
        )
        plant.setWatered(true)
        assertEquals(true, plant.getWatered())
    }
}


class PresetPlantsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPresetPlants() {
        val expectedPlants = listOf(
            PlantModel("Custom", image = R.drawable.edit_pencil, watered = false),
            PlantModel(
                "Monstera",
                description = "Monstera (Monstera deliciosa) is a popular tropical houseplant known for its large, glossy, heart-shaped leaves with unique holes and splits. It thrives in bright, indirect light and is low-maintenance.",
                frequency = 3,
                water = "300ml",
                image = R.drawable.monstera,
                watered = false
            ),
            PlantModel(
                "Orchid",
                description = "Orchids (Phalaenopsis) are elegant tropical plants known for their stunning, long-lasting flowers. They thrive in bright, indirect light and require careful watering and humidity.",
                frequency = 7,
                water = "100ml",
                image = R.drawable.orchid,
                watered = false
            ),
            PlantModel(
                "ZZ Plant",
                description = "The ZZ plant (Zamioculcas zamiifolia) is a hardy, low-maintenance indoor plant known for its glossy, dark green leaves.",
                frequency = 14,
                water = "250ml",
                image = R.drawable.zz_plant,
                watered = false
            ),
            PlantModel(
                "Snake Plant",
                description = "The Snake Plant is a resilient and low-maintenance plant with tall, sword-like leaves featuring green and yellow variegation.",
                frequency = 21,
                water = "300ml",
                image = R.drawable.snake_plant,
                watered = false
            ),
            PlantModel(
                "Spider Plant",
                description = "The Spider Plant is a hardy and adaptable houseplant with long, arching, variegated leaves.",
                frequency = 7,
                water = "300ml",
                image = R.drawable.spider_plant,
                watered = false
            ),
            PlantModel(
                "Aloe Vera",
                description = "Aloe Vera is a versatile succulent with thick, fleshy leaves filled with gel known for its medicinal and skincare benefits.",
                frequency = 14,
                water = "200ml",
                image = R.drawable.aloe_vera,
                watered = false
            ),
            PlantModel(
                "African Violet",
                description = "The African Violet is a compact houseplant with fuzzy leaves and vibrant flowers in purple, pink, or white.",
                frequency = 7,
                water = "200ml",
                image = R.drawable.african_violet,
                watered = false
            )
        )

        assertEquals(expectedPlants, PresetPlants.presetPlants)
    }


}

