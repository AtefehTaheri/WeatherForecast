package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.api

import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.ErrorResponse
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.ListWeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {

    @GET("forecast?")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") key: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric",
    ):NetworkResponse<ListWeatherForecast,ErrorResponse>

}