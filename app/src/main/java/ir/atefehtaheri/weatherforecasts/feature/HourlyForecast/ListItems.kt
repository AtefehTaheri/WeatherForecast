package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.WeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.geticonimage
import kotlin.math.round

@Composable
fun ListItems(item: WeatherForecastDataModel) {

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${round(item.temp).toInt()}°C",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 29.71.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color.White,
            )
        )
        Image(
            modifier = Modifier.size(66.dp),
            painter = painterResource(id = geticonimage(item.icon)),
            contentDescription = ""
        )
        Text(
            text = item.time,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 29.71.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = Color.White,
            )


        )

    }

}