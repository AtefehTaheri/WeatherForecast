package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model

import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel

data class ListWeatherForecast(
    val listweather: List<WeatherForecast>
)

fun ListWeatherForecast.toListWeatherForecastDataModel(): ListWeatherForecastDataModel {

    val list_item = this.listweather.map {

        val time= it.dt_txt.split("\\s|:".toRegex())
        WeatherForecastDataModel(it.main.temp, it.weather[0].icon, time[1]+":"+ time[2])
    }
    return ListWeatherForecastDataModel(list_item)

}