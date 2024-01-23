package ir.atefehtaheri.weatherforecasts.data.LocationManager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

const val DataStore_Name = "Location_DataStore"

val Context.dataStore by preferencesDataStore(
    name = DataStore_Name
)

class LocationDataStoreImpl @Inject constructor(private val context: Context) : LocationDataStore {

    val city = stringPreferencesKey("City")
    override suspend fun getLocation(): String? {
        return context.dataStore.data.first()[city]
    }

    override suspend fun setLocation(location: String) {
        context.dataStore.edit {
            it[city] = location
        }

    }
}