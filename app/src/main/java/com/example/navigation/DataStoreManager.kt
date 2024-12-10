package com.example.navigation

import com.example.navigation.models.packing.TripModel
import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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

    @SuppressLint("NewApi")
    fun addPlantToWatering(){

    }


}