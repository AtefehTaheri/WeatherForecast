package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models

import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.models.CurrentWeatherDataModel

data class CurrentWeatherDto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
fun CurrentWeatherDto.toCurrentWeatherDataModel(): CurrentWeatherDataModel {
    return CurrentWeatherDataModel(
        temp=main.temp,
        temp_max = main.temp_max,
        temp_min = main.temp_min,
        img=weather[0].icon,
        location = name,
        description = weather[0].description,
        longitude=coord.lon,
        latitude=coord.lat,
    )
}