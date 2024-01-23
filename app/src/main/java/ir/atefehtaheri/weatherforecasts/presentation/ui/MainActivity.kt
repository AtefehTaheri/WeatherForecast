package ir.atefehtaheri.weatherforecasts.presentation.ui

//import android.graphics.fonts.Font
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManager
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManagerScreen
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

                    LocationManager(CurrentWeatherViewModel,WeatherForecastViewModel)


//                    if (isLocationPermissonGranted(this)) {
//                        Toast.makeText(this, "is Granted", Toast.LENGTH_SHORT).show()
//
//                    }else{
//                        FirstScreen()
//                    }

//                        Column {
//
//                            CurrentLocationScreen()
//                            CurrentWeatherScreen(CurrentWeatherViewModel, WeatherForecastViewModel)
//                            WeatherForecastScreen(WeatherForecastViewModel)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForecastsTheme {
//        first_screen()
    }
}