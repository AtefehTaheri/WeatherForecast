package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel

//@SuppressLint("SuspiciousIndentation")
@Composable
fun LocationManager(
    CurrentWeatherViewModel: CurrentWeatherViewModel,
    WeatherForecastViewModel: WeatherForecastViewModel) {

    val LocationManagerViewModel: LocationManagerViewModel = viewModel()
    LocationManagerViewModel.setLocationDataStore("rasht")

    LocationManagerViewModel.locationState.value?.let{
        Log.d("TAG",it)
        CurrentWeatherViewModel.loadCurrentWeather(it)
        WeatherForecastViewModel.loadWeatherForecast(it)

    } ?: LocationManagerScreen(
        CurrentWeatherViewModel,
        WeatherForecastViewModel)


}