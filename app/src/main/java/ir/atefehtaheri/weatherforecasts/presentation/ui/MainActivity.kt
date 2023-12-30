package ir.atefehtaheri.weatherforecasts.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherScreen
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastScreen
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.WeatherForecastsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val CurrentWeatherViewModel: CurrentWeatherViewModel by viewModels()
    private val WeatherForecastViewModel: WeatherForecastViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            installSplashScreen()
            delay(4000)
        }
        setContent {
            WeatherForecastsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CurrentWeatherScreen(CurrentWeatherViewModel,WeatherForecastViewModel)
                        WeatherForecastScreen(WeatherForecastViewModel)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForecastsTheme {
        Greeting("Android")

    }
}