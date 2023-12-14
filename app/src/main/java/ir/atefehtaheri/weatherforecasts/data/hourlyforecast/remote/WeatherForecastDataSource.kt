package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.WeatherForecast

interface WeatherForecastDataSource {
    suspend fun getHourlyForecast(city: String): ResultStatus<WeatherForecast>
}