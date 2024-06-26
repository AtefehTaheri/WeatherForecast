package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.api.WeatherForecastApi
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.Item
import javax.inject.Inject

class WeatherForecastDataSourceImpl @Inject constructor(
    private val WeatherForecastApi: WeatherForecastApi
) :WeatherForecastDataSource {

    override suspend fun getHourlyForecast(lat: Double, lon: Double): ResultStatus<List<Item>> {
        return when(val result =WeatherForecastApi.getForecast(lat,lon)){
            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "Error")
            is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "Error")
        }
    }
}