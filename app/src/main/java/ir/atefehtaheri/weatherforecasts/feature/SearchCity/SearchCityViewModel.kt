//package ir.atefehtaheri.weatherforecasts.feature.SearchCity
//
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.compose.viewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
//import ir.atefehtaheri.weatherforecasts.data.SearchCity.repository.SearchCityRepository
//import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepository
//import ir.atefehtaheri.weatherforecasts.domain.usecase.PermissionUseCase
//import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState.CurrentWeatherState
//import ir.atefehtaheri.weatherforecasts.feature.LocationManager.UiState.LocationState
//import ir.atefehtaheri.weatherforecasts.feature.SearchCity.UiState.SuggestedListState
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class SearchCityViewModel @Inject constructor(
//    private val SearchCityRepository: SearchCityRepository,
//    private val CurrentLocationRepository: CurrentLocationRepository,
//    val PermissionUseCase: PermissionUseCase
//
//    ) : ViewModel() {
//
//
//    private val _SuggestedListState = mutableStateOf(SuggestedListState())
//    val SuggestedListState: State<SuggestedListState> = _SuggestedListState
//
////    private var _location = mutableStateOf(LocationState())
////    var location: State<LocationState> = _location
//
//    fun getsearch(city: String) {
//        _SuggestedListState.value =
//            _SuggestedListState.value.copy(true,null,null)
//
//        viewModelScope.launch {
//             when (val result = SearchCityRepository.getSuggestedList(city)) {
//                is ResultStatus.Failure -> _SuggestedListState.value =
//                    _SuggestedListState.value.copy(false,null,error = result.exception_message)
//
//                is ResultStatus.Success ->_SuggestedListState.value =
//                    _SuggestedListState.value.copy(false,result.data,null)
//
//             }
//        }
//    }
//
////    fun getCurrentLocation(context: Context) {
////        viewModelScope.launch {
////            _location.value = _location.value.copy(false)
////
////            CurrentLocationRepository.getLocation(
////                { latitude, longitude ->
////                    _location.value = _location.value.copy(true,null, latitude, longitude)
////                },
////                {
////                    _location.value = _location.value.copy(true,"Could not get location")
////                    Toast.makeText(context, "Could not get location", Toast.LENGTH_SHORT).show()
////                }
////            )
////        }
////    }
//
//}