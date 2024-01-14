package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState

import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.models.CurrentWeatherDataModel

data class CurrentWeatherState(
//    val error:Boolean
    val isLoading: Boolean = false,
    val CurrentWeatherDataModel: CurrentWeatherDataModel? = null,
    val error: String = ""
)
