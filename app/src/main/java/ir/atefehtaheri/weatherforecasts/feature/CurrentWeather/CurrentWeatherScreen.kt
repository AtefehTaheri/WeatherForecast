package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
//import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen (
    CurrentWeatherViewModel: CurrentWeatherViewModel,
    WeatherForecastViewModel: WeatherForecastViewModel
    ){


    var textState= remember { mutableStateOf("") }

    Column {
        TextField(value = textState.value,
            onValueChange = {
                textState.value = it
            },
            label = { Text("Enter City") })


        Button(onClick = {
            CurrentWeatherViewModel.loadCurrentWeather(textState.value)
            WeatherForecastViewModel.loadWeatherForecast(textState.value)
        }) {
       Text(text = "CurrentWeather")
        }
        Text(text = CurrentWeatherViewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.description ?: "")
        Text(text = CurrentWeatherViewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.location ?: "")
        Text(text = CurrentWeatherViewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.let{it.temp.toString()} ?: ""  )
        Text(text = CurrentWeatherViewModel.CurrentWeatherState.value.error ?: "qqq")

    }

}