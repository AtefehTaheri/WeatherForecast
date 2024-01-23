package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.CurrentWeatherViewModel
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.WeatherForecastViewModel
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Yellow

@Composable
fun LocationManagerScreen(
      CurrentWeatherViewModel: CurrentWeatherViewModel,
      WeatherForecastViewModel: WeatherForecastViewModel,
      modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    val ctx = LocalContext.current
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Toast.makeText(ctx, "get Location:Granted", Toast.LENGTH_SHORT).show()
            } else {
                showDialog=true
            }
        })

    val fontpoppins = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_light, FontWeight.Light),
    )
    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        )
    )

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

        ElevatedButton(
            onClick = {

                if (isLocationPermissonGranted(ctx)){

                } else{
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Yellow,
                contentColor = colorResource(R.color.blue)

            )
        ) {
            Text(
                text = "Current Location",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 29.71.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                )

            )
        }
        TextButton(
            onClick = { }
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Manual selection",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 29.71.sp,
                    fontFamily = fontpoppins,
                    fontWeight = FontWeight.Light,
                    color = Color.Black
                )
            )
        }
    }
    if(showDialog){
        PermissionDialog(onDismiss = { showDialog=false }, onGoToAppSettingsClick = ::openAppSettings)

        }
}


fun isLocationPermissonGranted(context: Context): Boolean {
    return (ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
            == PackageManager.PERMISSION_GRANTED)
}

fun openAppSettings(context: Context) {
    val intent=Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package",context.packageName, null)
    )
    context.startActivity(intent)
}
