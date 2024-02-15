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
import ir.atefehtaheri.weatherforecasts.feature.SearchCity.SearchCityScreen
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
                navArgument("latitude") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("longitude") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val latitude = it.arguments!!.getString("latitude")!!.toDouble()
            val longitude = it.arguments!!.getString("longitude")!!.toDouble()
            WeatherScreen(navController,latitude,longitude)
        }
        composable(route = Screen.SearchCity.route) {
            SearchCityScreen(navController)
        }
    }
}