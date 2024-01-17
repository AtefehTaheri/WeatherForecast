package ir.atefehtaheri.weatherforecasts.presentation.ui

import android.Manifest
//import android.graphics.fonts.Font
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.CurrentLocation.CurrentLocationScreen
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherScreen
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastScreen
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.WeatherForecastsTheme
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Yellow
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

                    first_screen()

//
//                        val ctx = LocalContext.current
//
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


@Composable
fun first_screen(modifier: Modifier = Modifier) {
    val fontpoppins = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_light, FontWeight.Light),
    )
    val gradient = Brush.verticalGradient(listOf(GradientC1, GradientC2))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(gradient),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "",
            Modifier
                .size(400.dp)
                .padding(30.dp), contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    ParagraphStyle(lineHeight = 77.12.sp)
                ) {
                    withStyle(
                        SpanStyle(
                            fontSize = 64.sp,
                            fontFamily = fontpoppins,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                        )
                    ) {
                        append("Weather \n")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 64.sp,
                            fontFamily = fontpoppins,
                            fontWeight = FontWeight.Medium,
                            color = Yellow,
                        )
                    ) {
                        append("ForeCasts")
                    }
                }

            }, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow,
            contentColor = colorResource(R.color.blue)
       )
            ) {
            Text(
                text = "Current Location",
                style = TextStyle(
                    fontSize = 28.sp,
                    lineHeight = 29.71.sp,
                    fontFamily = fontpoppins,
                    fontWeight = FontWeight.Black,
                )

            )
        }
        TextButton(
            onClick = {  }
        ) {
            Text(text="Manual selection",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 29.71.sp,
                    fontFamily = fontpoppins,
                    fontWeight = FontWeight.Light,
                    color = Color.Black
                ))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForecastsTheme {
        first_screen()
    }
}