package ir.atefehtaheri.weatherforecasts.feature.LocationManager.UiState

data class LocationState(
    val isReady: Boolean = false,
    val error: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
