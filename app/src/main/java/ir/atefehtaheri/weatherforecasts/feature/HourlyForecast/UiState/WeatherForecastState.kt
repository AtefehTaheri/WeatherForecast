package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.UiState

import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel

data class WeatherForecastState(
    val isLoading: Boolean = false,
    val ListWeatherForecastDataModel: ListWeatherForecastDataModel? = null,
    val error: String = ""

)
