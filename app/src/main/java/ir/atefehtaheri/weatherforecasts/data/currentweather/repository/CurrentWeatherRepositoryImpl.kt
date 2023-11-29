package ir.atefehtaheri.weatherforecasts.data.currentweather.repository

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.toCurrentWeatherDataModel
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.CurrentWeatherDataSource
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.models.CurrentWeatherDataModel
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val CurrentWeatherDataSource: CurrentWeatherDataSource
): CurrentWeatherRepository{
    override suspend fun getcurrentweather(city: String): ResultStatus<CurrentWeatherDataModel> {
        return when(val result =CurrentWeatherDataSource.getCurrentWeather(city)){
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.toCurrentWeatherDataModel() )
        }



    }
}