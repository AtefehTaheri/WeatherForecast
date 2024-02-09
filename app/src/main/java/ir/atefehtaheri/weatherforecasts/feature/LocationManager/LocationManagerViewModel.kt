package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import android.app.Activity
import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepository
import ir.atefehtaheri.weatherforecasts.domain.usecase.PermissionUseCase
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.UiState.LocationState
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationManagerViewModel @Inject constructor(
    private val LocationDataStore: LocationDataStore,
    private val CurrentLocationRepository: CurrentLocationRepository,
    val PermissionUseCase: PermissionUseCase

) : ViewModel() {

    private var _location = mutableStateOf(LocationState())
    var location: State<LocationState> = _location

    init {
        getLocationDataStore()
    }

    fun getLocationDataStore() {
        viewModelScope.launch {
            _location.value =
                _location.value.copy(false, null, null, null,null)
            val city =LocationDataStore.getLocation()
            _location.value =
                _location.value.copy(true, null, null, null,city )
        }
    }

//    private fun setLocationDataStore(location: String) {
//        viewModelScope.launch {
//            LocationDataStore.setLocation(location)
//        }
//    }

    fun getCurrentLocation(context: Context) {
        viewModelScope.launch {
            _location.value = _location.value.copy(false)

            CurrentLocationRepository.getLocation(
                { latitude, longitude ->
                    _location.value = _location.value.copy(true,null, latitude, longitude, null)
                },
                {
                    _location.value = _location.value.copy(true,"Could not get location")
                    Toast.makeText(context, "Could not get location", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}