package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.CurrentWeatherRepository
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState.CurrentWeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val CurrentWeatherRepository: CurrentWeatherRepository,
    private val LocationDataStore: LocationDataStore,
) : ViewModel() {

    private var _CurrentWeatherState = mutableStateOf(CurrentWeatherState())
    var CurrentWeatherState: State<CurrentWeatherState> = _CurrentWeatherState

    private fun setLocationDataStore(latitude: Double,longitude: Double) {
        viewModelScope.launch {
            LocationDataStore.setLocation(latitude,longitude)
        }
    }
    fun loadCurrentWeather(
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            _CurrentWeatherState.value = _CurrentWeatherState.value.copy(true, null, null)

            when (val result = CurrentWeatherRepository.getcurrentweather(latitude, longitude)) {
                is ResultStatus.Failure -> {
                    _CurrentWeatherState.value =
                        _CurrentWeatherState.value.copy(
                            false,
                            null,
                            error = result.exception_message
                        )
                }
                is ResultStatus.Success -> {
                    _CurrentWeatherState.value =
                        _CurrentWeatherState.value.copy(
                            false,
                            CurrentWeatherDataModel = result.data,
                            null
                        )
                    setLocationDataStore(result.data!!.latitude,result.data!!.longitude)
                }
            }
        }
    }
}