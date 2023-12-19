package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model

import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel

data class Item(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)


fun Item.toWeatherForecastDataModel(): WeatherForecastDataModel {

    val time = this.dt_txt.split("\\s|:".toRegex())
    return WeatherForecastDataModel(this.main.temp, this.weather[0].icon, time[1] + ":" + time[2])

}