package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.UiState.WeatherForecastState


@Composable
fun WeatherForecastCard(WeatherForecastState: WeatherForecastState) {

    var items =
        WeatherForecastState.ListWeatherForecastDataModel?.items ?: emptyList()
    Column() {
        LazyRow() {
            items(items) {
                ListItems(it)
            }
        }
        Text(text = WeatherForecastState.error)
    }
    
    
}