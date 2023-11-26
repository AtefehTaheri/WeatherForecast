package ir.atefehtaheri.weatherforecasts.data.currentweather.remote

interface CurrentWeatherDataSource {

    fun getCurrentWeather(city:String):
}