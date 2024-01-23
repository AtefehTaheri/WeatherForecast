package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationManagerViewModel @Inject constructor(private val LocationDataStore: LocationDataStore) : ViewModel() {

    private val _locationState = mutableStateOf<String?>(null)
    val locationState: MutableState<String?> = _locationState

    init {
        getLocationDataStore()
    }

    fun getLocationDataStore() {
        viewModelScope.launch {
            _locationState.value = LocationDataStore.getLocation()

        }

    }

    fun setLocationDataStore(location:String) {
        viewModelScope.launch {
            LocationDataStore.setLocation(location)

        }

    }
}