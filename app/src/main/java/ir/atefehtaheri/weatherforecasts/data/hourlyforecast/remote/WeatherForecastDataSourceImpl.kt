package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.api.WeatherForecastApi
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.WeatherForecast
import javax.inject.Inject

class WeatherForecastDataSourceImpl @Inject constructor(
    private val WeatherForecastApi: WeatherForecastApi
) :WeatherForecastDataSource {
    override suspend fun getHourlyForecast(city: String): ResultStatus<WeatherForecast> {
        return when(val result =WeatherForecastApi.getForecast(city)){
            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "Error")
            is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "Error")
        }
    }
}