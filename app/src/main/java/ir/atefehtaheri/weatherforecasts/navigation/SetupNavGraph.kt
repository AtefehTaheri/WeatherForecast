package ir.atefehtaheri.weatherforecasts.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManagerScreen
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManagerViewModel
import ir.atefehtaheri.weatherforecasts.presentation.ui.WeatherScreen
import ir.atefehtaheri.weatherforecasts.presentation.ui.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    LocationManagerViewModel: LocationManagerViewModel= hiltViewModel()
) {

    NavHost(navController = navController, startDestination =  Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController, LocationManagerViewModel = LocationManagerViewModel)
        }

        composable(route = Screen.LocationManager.route) {
            LocationManagerScreen(navController,LocationManagerViewModel=LocationManagerViewModel)
        }

        composable(
            route = Screen.Weather.route,
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable=true
                },
                navArgument("latitude") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable=true
                },
                navArgument("longitude") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable=true
                }
            )
        ) {
            val city = it.arguments?.getString("city")
            val latitude = it.arguments?.getString("latitude")?.toDouble()
            val longitude = it.arguments?.getString("longitude")?.toDouble()
            WeatherScreen(city,latitude,longitude)
        }
    }
}