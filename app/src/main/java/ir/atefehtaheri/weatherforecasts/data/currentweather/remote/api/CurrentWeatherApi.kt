package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.api

import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.CurrentWeatherDto
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") city:String,
        @Query("appid") key:String= BuildConfig.API_KEY,
        @Query("units") units:String="metric",
    ): NetworkResponse<CurrentWeatherDto,ErrorResponse>

}