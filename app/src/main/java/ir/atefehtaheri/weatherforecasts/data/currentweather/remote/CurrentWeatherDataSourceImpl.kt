package ir.atefehtaheri.weatherforecasts.data.currentweather.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.api.CurrentWeatherApi
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.CurrentWeatherDto
import javax.inject.Inject

class CurrentWeatherDataSourceImpl @Inject constructor(
    private val CurrentWeatherApi: CurrentWeatherApi
) : CurrentWeatherDataSource {


    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): ResultStatus<CurrentWeatherDto> {

        return when (val result = CurrentWeatherApi.getCurrentWeather(lat, lon)) {
            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(
                result.error.message ?: "NetworkError"
            )

            is NetworkResponse.UnknownError -> ResultStatus.Failure(
                result.error.message ?: "UnknownError"
            )
        }
    }
}