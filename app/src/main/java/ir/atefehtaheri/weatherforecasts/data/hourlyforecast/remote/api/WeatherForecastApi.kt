package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.api

import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.ErrorResponse
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.Item
//import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.WeatherForecastApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {



    @GET("/data/2.5/forecast?")
    suspend fun getForecast(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") key: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric",
    ):NetworkResponse<List<Item>,ErrorResponse>
}