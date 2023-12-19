package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel


@Composable
fun WeatherForecastScreen(viewModel: WeatherForecastViewModel) {

    var items =
        viewModel.WeatherForecastState.value.ListWeatherForecastDataModel?.items ?: emptyList()
    Column {
        LazyRow {
            items(items) {
                ListItems(it)
            }
        }
        Text(text = viewModel.WeatherForecastState.value.error)
    }
    
    
}