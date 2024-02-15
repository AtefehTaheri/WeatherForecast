package ir.atefehtaheri.weatherforecasts.feature.SearchCity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListItem
import ir.atefehtaheri.weatherforecasts.navigation.Screen.LocationManager.navigateToWeatherScreen
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC3

@Composable
fun SuggestListItemScreen(
    navController: NavController,
    SuggestedListItem: SuggestedListItem
) {


    val gradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.5f to GradientC3,
            1f to GradientC1
        ),
        start = Offset(Float.POSITIVE_INFINITY, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            navController.navigate(
                navigateToWeatherScreen(
                    SuggestedListItem.lat,
                    SuggestedListItem.lon
                )
            ) {
                launchSingleTop = true
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = gradient)
                .padding(horizontal = 10.dp)
        ) {

            Text(
                text = SuggestedListItem.name, style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontWeight = FontWeight(600),
                    color = Color.White,
                    letterSpacing = 0.47.sp,
                )
            )

            Text(
                text = "${SuggestedListItem.state}, ${SuggestedListItem.country}",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 29.71.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                    fontWeight = FontWeight(400),
                    color = Color.White,
                    letterSpacing = 0.47.sp,
                )
            )
        }
    }

}