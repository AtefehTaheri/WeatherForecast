package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState.CurrentWeatherState
import ir.atefehtaheri.weatherforecasts.presentation.ui.shimmerEffect
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.LoadingBackground
import kotlin.math.round

@Composable
fun CurrentWeatherCard(CurrentWeatherState: CurrentWeatherState, modifier: Modifier) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        CurrentWeatherState.CurrentWeatherDataModel?.let { DataModel ->
            Column(

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = geticonimage(DataModel.img)),
                    contentDescription = "",
                    Modifier.size(180.dp).padding(top=40.dp), contentScale = ContentScale.Fit
                )
                Text(
                    text = "${round(DataModel.temp).toInt()}°C",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = "${DataModel.description}\nMax: ${DataModel.temp_max}°C   Min:${DataModel.temp_min}°C",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 29.71.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.47.sp,
                    )
                )
            }
        }


        AnimatedVisibility(
            visible = CurrentWeatherState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(10.dp)
                    .shimmerEffect()
            )
        }

        CurrentWeatherState.error?.let { error ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
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


fun geticonimage(icon: String): Int {
    var iconDrawable = when (icon) {
        "01d" -> R.drawable.d1
        "02d" -> R.drawable.d2
        "03d" -> R.drawable.dn3dn4
        "04d" -> R.drawable.dn3dn4
        "09d" -> R.drawable.dn9
        "10d" -> R.drawable.d10
        "11d" -> R.drawable.dn11
        "13d" -> R.drawable.dn13
        "50d" -> R.drawable.dn50
        "01n" -> R.drawable.n1
        "02n" -> R.drawable.n2
        "03n" -> R.drawable.dn3dn4
        "04n" -> R.drawable.dn3dn4
        "09n" -> R.drawable.dn9
        "10n" -> R.drawable.n10
        "11n" -> R.drawable.dn11
        "13n" -> R.drawable.dn13
        "50n" -> R.drawable.dn50
        else -> 0
    }
    return iconDrawable
}