package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepository
import ir.atefehtaheri.weatherforecasts.feature.HourlyForecast.UiState.WeatherForecastState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val WeatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _WeatherForecastState = mutableStateOf(WeatherForecastState())
    val WeatherForecastState: State<WeatherForecastState> = _WeatherForecastState


    fun loadWeatherForecast(
        city: String?,
        latitude: Double?,
        longitude: Double?,
    ) {

        viewModelScope.launch {
            _WeatherForecastState.value = _WeatherForecastState.value.copy(true, null, null)
            city?.let {
                getWeatherForecast(city)

            } ?: getWeatherForecast(latitude!!, longitude!!)
        }
    }




    fun getWeatherForecast(city: String){

        viewModelScope.launch {

        when (val result = WeatherForecastRepository.getListWeatherForecast(city)) {
            is ResultStatus.Failure -> {
                _WeatherForecastState.value =
                    _WeatherForecastState.value.copy(false,error = result.exception_message)
            }
            is ResultStatus.Success -> {
                _WeatherForecastState.value =
                _WeatherForecastState.value.copy(false,ListWeatherForecastDataModel = result.data,null)
        }}

    }}
    fun getWeatherForecast(lat:Double,lon:Double){
Log.d("TAG",lat.toString())
        Log.d("TAG",lon.toString())

        viewModelScope.launch {

            when (val result = WeatherForecastRepository.getListWeatherForecast(lat,lon)) {
                is ResultStatus.Failure -> {
                    _WeatherForecastState.value =
                        _WeatherForecastState.value.copy(false,error = result.exception_message)
                }
                is ResultStatus.Success -> {
                    _WeatherForecastState.value =
                        _WeatherForecastState.value.copy(false,ListWeatherForecastDataModel = result.data,null)
                }}

        }}

}