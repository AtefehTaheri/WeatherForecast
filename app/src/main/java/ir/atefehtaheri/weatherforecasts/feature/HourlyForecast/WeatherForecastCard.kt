package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState.CurrentWeatherState
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.UiState.WeatherForecastState
import ir.atefehtaheri.weatherforecasts.presentation.ui.shimmerEffect
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.LoadingBackground


@Composable
fun WeatherForecastCard(WeatherForecastState: WeatherForecastState,city: CurrentWeatherState) {

    val gradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        ),
        start = Offset(Float.POSITIVE_INFINITY, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        WeatherForecastState.ListWeatherForecastDataModel?.let {

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradient)
                ) {
                    TextButton(modifier = Modifier
                        .fillMaxWidth(),
                        onClick = { /*TODO*/ }) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location),
                                contentDescription = "",
                                tint = Color.White
                            )
                            Text(
                                text = city.CurrentWeatherDataModel!!.location,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    color = Color.White
                                )
                            )
                        }
                    }
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    LazyRow() {
                        items(it.items) {
                            ListItems(it)
                        }
                    }
                }
            }
        }

//        AnimatedVisibility(
//            visible = WeatherForecastState.isLoading,
//            enter = fadeIn(),
//            exit = fadeOut()
//        ) {
//            Box(
//                modifier = Modifier
//                    .background(Color.Black.copy(alpha = 0.5f))
//            ) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(50.dp)
//                        .align(Alignment.Center)
//                )
//            }
//        }
//        WeatherForecastState.error?.let { error ->
//            Text(
//                text = error,
//                color = Color.Red,
//                textAlign = TextAlign.Center,
//                )
//        }


        AnimatedVisibility(
            visible = WeatherForecastState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp)
                    .shimmerEffect()
            )
        }

        WeatherForecastState.error?.let { error ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp).background(LoadingBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =Arrangement.Center
            ) {
                Text(
                    text = error,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}