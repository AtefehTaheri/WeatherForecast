package ir.atefehtaheri.weatherforecasts.data.currentLocation

interface CurrentLocation {
     suspend fun getLocation():Boolean
}