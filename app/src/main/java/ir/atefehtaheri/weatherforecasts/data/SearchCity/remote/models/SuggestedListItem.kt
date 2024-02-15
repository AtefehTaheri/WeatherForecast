package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models

data class SuggestedListItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)