package ir.atefehtaheri.weatherforecasts.data.currentLocation.repository

interface CurrentLocationRepository {
    suspend fun getLocation(
        onSuccess: (latitude: Double,longitude:Double) -> Unit,
        onFailure: () -> Unit,
    )
}