package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel

interface WeatherForecastRepository {

    suspend fun getListWeatherForecast(lat:Double,lon:Double):ResultStatus<ListWeatherForecastDataModel>


}