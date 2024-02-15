package ir.atefehtaheri.weatherforecasts.data.currentweather.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.CurrentWeatherDto

interface CurrentWeatherDataSource {

    suspend fun getCurrentWeather(lat:Double,lon:Double):ResultStatus<CurrentWeatherDto>

}