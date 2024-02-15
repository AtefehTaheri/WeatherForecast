package ir.atefehtaheri.weatherforecasts.data.LocationManager

import ir.atefehtaheri.weatherforecasts.data.LocationManager.model.Location

interface LocationDataStore {
    suspend fun getLocation(): Location
    suspend fun setLocation(latitude:Double,longitude:Double)
}