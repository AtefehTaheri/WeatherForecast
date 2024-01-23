package ir.atefehtaheri.weatherforecasts.data.LocationManager

interface LocationDataStore {
    suspend fun getLocation():String?

    suspend fun setLocation(location:String)
}