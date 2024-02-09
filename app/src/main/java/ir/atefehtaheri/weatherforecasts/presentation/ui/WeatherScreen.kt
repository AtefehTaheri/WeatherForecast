package ir.atefehtaheri.weatherforecasts.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherCard
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2


@Composable
fun WeatherScreen(
    city: String?,
    latitude: Double?,
    longitude: Double?,
    CurrentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()

) {

    LaunchedEffect(Unit) {
        CurrentWeatherViewModel.loadCurrentWeather(city, latitude, longitude)
    }

    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CurrentWeatherCard(CurrentWeatherViewModel.CurrentWeatherState.value, Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.house),
            contentDescription = "image description",
            Modifier.weight(1f)
                .width(336.dp)
                .height(198.dp), contentScale = ContentScale.Fit
        )

    }

}
