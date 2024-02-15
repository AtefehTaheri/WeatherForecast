package ir.atefehtaheri.weatherforecasts.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splashscreen")
    object SearchCity:Screen("SearchCityScreen")

    object LocationManager : Screen("location_manager")
    object Weather : Screen("weather_screen?latitude={latitude}&longitude={longitude}")
    fun navigateToWeatherScreen(latitude:Double,longitude:Double): String {
        return "weather_screen?latitude=${latitude}&longitude=${longitude}"
    }

}