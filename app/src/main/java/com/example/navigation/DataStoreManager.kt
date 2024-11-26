package com.example.navigation

import TripModel
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val TRIP_DATASTORE = "trip_data"
val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = TRIP_DATASTORE)

class DataStoreManager(private val context: Context) {
    companion object {
        val TRIPS_KEY = stringPreferencesKey("trips")
    }

    suspend fun saveTrips(trips: List<TripModel>) {
        val jsonString = Json.encodeToString(trips)
        context.preferenceDataStore.edit {
            it[TRIPS_KEY] = jsonString
        }
    }

    fun getTrips() = context.preferenceDataStore.data.map { preferences ->
        val jsonString = preferences[TRIPS_KEY] ?: "[]"
        Json.decodeFromString<List<TripModel>>(jsonString)
    }
}