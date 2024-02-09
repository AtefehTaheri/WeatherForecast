package ir.atefehtaheri.weatherforecasts.data.currentLocation.repository

import android.annotation.SuppressLint
import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import javax.inject.Inject

class CurrentLocationRepositoryImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    ) : CurrentLocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(
        onSuccess: (latitude: Double,longitude:Double) -> Unit,
        onFailure: () -> Unit,
    ) {

        locationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->

            location?.let {
                onSuccess(location.latitude,location.longitude)
            } ?: onFailure()
        }.addOnFailureListener {
            onFailure()
        }
    }
}
