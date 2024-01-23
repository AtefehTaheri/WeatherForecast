package ir.atefehtaheri.weatherforecasts.data.currentLocation

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class CurrentLocationmpl(private val application: Application): CurrentLocation {
    override suspend fun getLocation() :Boolean{
        return (ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED)
    }


}