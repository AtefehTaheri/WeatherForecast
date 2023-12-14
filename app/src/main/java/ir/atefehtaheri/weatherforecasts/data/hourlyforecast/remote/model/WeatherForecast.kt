package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model

import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel

data class WeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item>,
    val message: Int
)


fun WeatherForecast.toListWeatherForecastDataModel(): ListWeatherForecastDataModel
{
    val listItem=this.list.map {
        val time = it.dt_txt.split("\\s|:".toRegex())
        WeatherForecastDataModel(it.main.temp,it.weather[0].icon,time[1]+":"+time[2])
    }
    return ListWeatherForecastDataModel(listItem)
}