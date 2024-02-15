package ir.atefehtaheri.weatherforecasts.data.LocationManager

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import ir.atefehtaheri.weatherforecasts.data.LocationManager.model.Location
import kotlinx.coroutines.flow.first
import javax.inject.Inject

const val DataStore_Name = "Location_DataStore"

val Context.dataStore by preferencesDataStore(
    name = DataStore_Name
)

class LocationDataStoreImpl @Inject constructor(private val context: Context) : LocationDataStore {

    val lat = doublePreferencesKey("lat")
    val lon = doublePreferencesKey("lon")

    override suspend fun getLocation(): Location {
        return Location(context.dataStore.data.first()[lat],context.dataStore.data.first()[lon])
    }

    override suspend fun setLocation(latitude:Double,longitude:Double) {
        context.dataStore.edit {
            it[lat] = latitude
            it[lon] = longitude
        }

    }
}