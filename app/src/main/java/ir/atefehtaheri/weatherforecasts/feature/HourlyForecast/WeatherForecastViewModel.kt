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


    fun loadWeatherForecast(city: String) = viewModelScope.launch {

        when (val result = WeatherForecastRepository.getListWeatherForecast(city)) {
            is ResultStatus.Failure -> {
                _WeatherForecastState.value =
                    _WeatherForecastState.value.copy(error = result.exception_message)
            }
            is ResultStatus.Success -> {
                _WeatherForecastState.value =
                _WeatherForecastState.value.copy(ListWeatherForecastDataModel = result.data)
        }}

    }


}