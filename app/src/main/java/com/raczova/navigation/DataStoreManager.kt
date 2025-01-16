package com.raczova.navigation

import com.raczova.navigation.models.packing.TripModel
import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.raczova.navigation.models.watering.PlantModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val TRIP_DATASTORE = "trip_data"
val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = TRIP_DATASTORE)

class DataStoreManager(private val context: Context) {
    companion object {
        val TRIPS_KEY = stringPreferencesKey("trips")
        val PLANTS_KEY = stringPreferencesKey("plants")
        val LANGUAGE_KEY = stringPreferencesKey("language")

    }

    @SuppressLint("NewApi")
    private val dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy")

    @SuppressLint("NewApi")
    suspend fun saveTrips(trips: List<TripModel>) {
        val sortedTrips = trips.sortedBy { trip ->
            LocalDate.parse(trip.startDate, dateFormatter)
        }
        val jsonString = Json.encodeToString(sortedTrips)
        context.preferenceDataStore.edit {
            it[TRIPS_KEY] = jsonString
        }
    }

    @SuppressLint("NewApi")
    fun getTrips() = context.preferenceDataStore.data.map { preferences ->
        val jsonString = preferences[TRIPS_KEY] ?: "[]"
        Json.decodeFromString<List<TripModel>>(jsonString)
            .filter { trip ->
                LocalDate.parse(trip.startDate, dateFormatter) >= LocalDate.now()
            }
            .sortedBy { trip ->
                LocalDate.parse(trip.startDate, dateFormatter)
            }
    }
    suspend fun saveLanguage(languageCode: String) {
        context.preferenceDataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = languageCode
        }
    }

    fun getLanguage() = context.preferenceDataStore.data.map { preferences ->
        preferences[LANGUAGE_KEY] ?: getSystemLanguage()
    }

    private fun getSystemLanguage(): String {
        return context.resources.configuration.locales[0].language ?: "en"
    }

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun savePlants(plants: List<PlantModel>) {
        val jsonString = json.encodeToString(plants)
        context.preferenceDataStore.edit { preferences ->
            preferences[PLANTS_KEY] = jsonString
        }
    }

    fun getPlants() = context.preferenceDataStore.data.map { preferences ->
        val jsonString = preferences[PLANTS_KEY]
        try {
            if (!jsonString.isNullOrBlank()) {
                json.decodeFromString<List<PlantModel>>(jsonString)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun deletePlant(plantId: String) {
        val currentPlants = getPlants().first()
        val updatedPlants = currentPlants.filter { it.id != plantId }
        savePlants(updatedPlants)
    }

    suspend fun clearData() {
        context.preferenceDataStore.edit { preferences ->
            preferences.clear()
        }
    }


}


