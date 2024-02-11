package ir.atefehtaheri.weatherforecasts.presentation.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManagerViewModel
import ir.atefehtaheri.weatherforecasts.navigation.Screen
import ir.atefehtaheri.weatherforecasts.navigation.Screen.LocationManager.navigateToWeatherScreen
import ir.atefehtaheri.weatherforecasts.navigation.SetupNavGraph
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.WeatherForecastsTheme
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            WeatherForecastsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController, LocationManagerViewModel: LocationManagerViewModel) {

    LaunchedEffect(LocationManagerViewModel.location.value.isReady) {
        if (LocationManagerViewModel.location.value.isReady) {
            delay(2000)
            LocationManagerViewModel.location.value.city?.let {

                navController.navigate(
                    navigateToWeatherScreen(LocationManagerViewModel.location.value.city)
                ) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            } ?: navController.navigate(Screen.LocationManager.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashlogo),
            contentDescription = "",
            Modifier.size(192.dp), contentScale = ContentScale.Fit
        )
    }
}