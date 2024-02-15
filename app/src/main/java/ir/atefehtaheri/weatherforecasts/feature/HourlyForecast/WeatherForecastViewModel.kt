package ir.atefehtaheri.weatherforecasts.feature.HourlyForecast

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
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            _WeatherForecastState.value = _WeatherForecastState.value.copy(true, null, null)

            when (val result = WeatherForecastRepository.getListWeatherForecast(latitude,longitude)) {
                is ResultStatus.Failure -> {
                    _WeatherForecastState.value =
                        _WeatherForecastState.value.copy(false,error = result.exception_message)
                }
                is ResultStatus.Success -> {
                    _WeatherForecastState.value =
                        _WeatherForecastState.value.copy(false,ListWeatherForecastDataModel = result.data,null)
                }}
        }
    }
}