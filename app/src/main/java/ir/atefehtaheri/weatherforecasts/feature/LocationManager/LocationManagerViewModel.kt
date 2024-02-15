package ir.atefehtaheri.weatherforecasts.feature.LocationManager


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import ir.atefehtaheri.weatherforecasts.data.SearchCity.repository.SearchCityRepository
import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepository
import ir.atefehtaheri.weatherforecasts.domain.usecase.PermissionUseCase
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.UiState.LocationState
import ir.atefehtaheri.weatherforecasts.feature.SearchCity.UiState.SuggestedListState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationManagerViewModel @Inject constructor(
    private val LocationDataStore: LocationDataStore,
    private val CurrentLocationRepository: CurrentLocationRepository,
    private val SearchCityRepository: SearchCityRepository,
    val PermissionUseCase: PermissionUseCase

) : ViewModel() {

    private var _location = mutableStateOf(LocationState())
    var location: State<LocationState> = _location

    private val _SuggestedListState = mutableStateOf(SuggestedListState())
    val SuggestedListState: State<SuggestedListState> = _SuggestedListState

    init {
        getLocationDataStore()
    }

    fun getLocationDataStore() {
        viewModelScope.launch {
            _location.value =
                _location.value.copy(false, null,null,null )
            val city =LocationDataStore.getLocation()
            _location.value =
                _location.value.copy(true, null, city.lat, city.lon )
        }
    }

    fun getCurrentLocation(context: Context) {
        viewModelScope.launch {
            _location.value = _location.value.copy(false)

            CurrentLocationRepository.getLocation(
                { latitude, longitude ->
                    _location.value = _location.value.copy(true,null, latitude, longitude)
                },
                {
                    _location.value = _location.value.copy(true,"Could not get location")
                    Toast.makeText(context, "Could not get location", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    fun getsearch(city: String) {
        _SuggestedListState.value =
            _SuggestedListState.value.copy(true,null,null)

        viewModelScope.launch {
            when (val result = SearchCityRepository.getSuggestedList(city)) {
                is ResultStatus.Failure -> _SuggestedListState.value =
                    _SuggestedListState.value.copy(false,null,error = result.exception_message)

                is ResultStatus.Success ->_SuggestedListState.value =
                    _SuggestedListState.value.copy(false,result.data,null)

            }
        }
    }
}