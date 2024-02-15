package ir.atefehtaheri.weatherforecasts.feature.LocationManager.UiState

import ir.atefehtaheri.weatherforecasts.data.LocationManager.model.Location

data class LocationState(
    val isReady: Boolean = false,
    val error: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
