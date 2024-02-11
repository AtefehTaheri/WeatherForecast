package ir.atefehtaheri.weatherforecasts.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherCard
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastCard
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel
import ir.atefehtaheri.weatherforecasts.navigation.Screen
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.LoadingBackground
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.WeatherForecastsTheme


@Composable
fun WeatherScreen(
    navController: NavController,
    city: String?,
    latitude: Double?,
    longitude: Double?,
    CurrentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
    WeatherForecastViewModel: WeatherForecastViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        CurrentWeatherViewModel.loadCurrentWeather(city, latitude, longitude)
        WeatherForecastViewModel.loadWeatherForecast(city, latitude, longitude)
    }

    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        )
    )

    val refresh= rememberSwipeRefreshState(isRefreshing = CurrentWeatherViewModel.CurrentWeatherState.value.isLoading)
SwipeRefresh(state = refresh
    , onRefresh = {

    CurrentWeatherViewModel.loadCurrentWeather(city, latitude, longitude)
    WeatherForecastViewModel.loadWeatherForecast(city, latitude, longitude)
}) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CurrentWeatherCard(
            CurrentWeatherViewModel.CurrentWeatherState.value, Modifier
        )

        Image(
            painter = painterResource(id = R.drawable.house),
            contentDescription = "",
            Modifier
                .width(336.dp)
                .height(198.dp), contentScale = ContentScale.Fit
        )
        WeatherForecastCard(
            WeatherForecastViewModel.WeatherForecastState.value,
            CurrentWeatherViewModel.CurrentWeatherState.value
        )
    }}
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                LoadingBackground,
                GradientC1,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}