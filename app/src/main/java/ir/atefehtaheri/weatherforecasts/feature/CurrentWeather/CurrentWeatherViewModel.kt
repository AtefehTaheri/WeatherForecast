package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepository
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

    private fun setLocationDataStore(location: String) {
        viewModelScope.launch {
            LocationDataStore.setLocation(location)
        }
    }

    fun loadCurrentWeather(
        city: String?,
        latitude: Double?,
        longitude: Double?,
    ) {
        viewModelScope.launch {
            _CurrentWeatherState.value = _CurrentWeatherState.value.copy(true, null, null)
            city?.let {
                getCurrentWeather(city)

            } ?: getCurrentWeather(latitude!!, longitude!!)
        }
    }

    private fun getCurrentWeather(city: String) {
        viewModelScope.launch {

            when (val result = CurrentWeatherRepository.getcurrentweather(city)) {
                is ResultStatus.Failure -> _CurrentWeatherState.value =
                    _CurrentWeatherState.value.copy(false, null, error = result.exception_message)

                is ResultStatus.Success -> {
                    _CurrentWeatherState.value =
                        _CurrentWeatherState.value.copy(
                            false,
                            CurrentWeatherDataModel = result.data, null)
                    setLocationDataStore(result.data!!.location)
                }
            }

        }
    }

    private fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ) {
        Log.d("TAG","latitude"+latitude.toString())
        Log.d("TAG","longitude"+longitude.toString())

        viewModelScope.launch {

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
                    Log.d("TAG","result.data"+result.data!!.location)
                    setLocationDataStore(result.data!!.location)

                }
            }
        }
    }
}