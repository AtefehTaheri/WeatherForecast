package ir.atefehtaheri.weatherforecasts.domain.usecase

import android.content.Context
import android.location.LocationManager

class CheckGps() {

    operator fun invoke(context: Context):Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    }
}