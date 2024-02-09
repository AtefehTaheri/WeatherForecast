package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.geticonimage

@Composable
fun ListItems (item: WeatherForecastDataModel){
    Card {
        Text(text = item.temp.toString())
        Image(painter = painterResource(id = geticonimage(item.icon)), contentDescription ="" )
        Text(text = item.icon)
        Text(text = item.time)

    }

}