package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel

@Composable
fun ListItems (item: WeatherForecastDataModel){
//    WeatherForecastDataModel
    Card {
        Text(text = item.temp.toString())
        Text(text = item.icon)
        Text(text = item.time)
//        Text(text = item)

    }

}