package ir.atefehtaheri.weatherforecasts.data.currentweather.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.CurrentWeatherDto
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.api.CurrentWeatherApi
import javax.inject.Inject

class CurrentWeatherDataSourceImpl @Inject constructor(
    private val CurrentWeatherApi: CurrentWeatherApi
):CurrentWeatherDataSource {
    override suspend fun getCurrentWeather(city: String): ResultStatus<CurrentWeatherDto> {
        return when(val result = CurrentWeatherApi.getCurrentWeather(city)) {
            is NetworkResponse.Success ->ResultStatus.Success(result.body)
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message+"222222" ?: "Error")
            is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "Error")
        }
    }
}