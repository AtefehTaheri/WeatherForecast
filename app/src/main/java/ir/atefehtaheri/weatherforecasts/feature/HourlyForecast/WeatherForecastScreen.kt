package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel


@Composable
fun WeatherForecastScreen(viewModel: WeatherForecastViewModel) {
    Log.i("TAG", "Info WeatherForecastScreen log message")

    var items =
        viewModel.WeatherForecastState.value.ListWeatherForecastDataModel?.items ?: emptyList()
    LazyRow {
        items(items) {
            ListItems(it)
        }
    }
}