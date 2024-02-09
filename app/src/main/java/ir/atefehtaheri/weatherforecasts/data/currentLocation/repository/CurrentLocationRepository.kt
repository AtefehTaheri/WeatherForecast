package ir.atefehtaheri.weatherforecasts.data.currentLocation.repository

import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient

interface CurrentLocationRepository {
    suspend fun getLocation(
        onSuccess: (latitude: Double,longitude:Double) -> Unit,
        onFailure: () -> Unit,
    )
}