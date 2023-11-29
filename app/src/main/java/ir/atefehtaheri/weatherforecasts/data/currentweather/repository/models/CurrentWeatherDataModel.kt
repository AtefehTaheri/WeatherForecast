package ir.atefehtaheri.weatherforecasts.data.currentweather.repository.models

data class CurrentWeatherDataModel(
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val img : String,
    val location: String,
    val description: String
)
