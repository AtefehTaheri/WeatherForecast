package ir.atefehtaheri.weatherforecasts.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splashscreen")
    object LocationManager : Screen("location_manager")
    object Weather : Screen("weather_screen?city={city}&latitude={latitude}&longitude={longitude}")
    fun navigateToWeatherScreen(city: String? = null,latitude:String?=null,longitude:String?=null): String {
        return "weather_screen?city=$city&latitude=$latitude&longitude=$longitude"
    }

}