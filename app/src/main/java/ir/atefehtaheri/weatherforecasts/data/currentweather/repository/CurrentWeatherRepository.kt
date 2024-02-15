package ir.atefehtaheri.weatherforecasts.data.currentweather.repository

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.models.CurrentWeatherDataModel

interface CurrentWeatherRepository {

    suspend fun getcurrentweather(lat: Double,lon:Double): ResultStatus<CurrentWeatherDataModel>

}