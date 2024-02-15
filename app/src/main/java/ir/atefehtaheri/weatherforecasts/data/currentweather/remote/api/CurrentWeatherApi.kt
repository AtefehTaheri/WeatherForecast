package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.api

import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.ErrorResponse
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {


    @GET("/data/2.5/weather?")
    suspend fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") key:String= BuildConfig.API_KEY,
        @Query("units") units:String="metric",
    ): NetworkResponse<CurrentWeatherDto, ErrorResponse>

}