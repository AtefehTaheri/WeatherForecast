package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)