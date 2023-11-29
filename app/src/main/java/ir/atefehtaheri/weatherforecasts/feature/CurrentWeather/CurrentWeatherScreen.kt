package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen (viewModel: CurrentWeatherViewModel){


    var textState= remember { mutableStateOf("") }

    Column {
        TextField(value = textState.value,
            onValueChange = {
                textState.value = it
            },
            label = { Text("Enter City") })

        Button(onClick = {viewModel.loadCurrentWeather(textState.value) }) {
       Text(text = "CurrentWeather")
        }
        Text(text = viewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.description ?: "")
        Text(text = viewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.location ?: "")
        Text(text = viewModel.CurrentWeatherState.value.CurrentWeatherDataModel?.temp.toString() )
    }

}