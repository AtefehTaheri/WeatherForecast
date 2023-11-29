package ir.atefehtaheri.weatherforecasts.feature.CurrentWeather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.CurrentWeatherRepository
import ir.atefehtaheri.weatherforecasts.feature.CurrentWeather.UiState.CurrentWeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val CurrentWeatherRepository: CurrentWeatherRepository
):ViewModel() {

   private val _CurrentWeatherState = mutableStateOf(CurrentWeatherState())
       val CurrentWeatherState: State<CurrentWeatherState> = _CurrentWeatherState


     fun loadCurrentWeather(city:String)=viewModelScope.launch{
        when(val result=CurrentWeatherRepository.getcurrentweather(city)){
            is ResultStatus.Failure -> _CurrentWeatherState.value=CurrentWeatherState.value.copy(error = result.exception_message)
            is ResultStatus.Success -> _CurrentWeatherState.value=CurrentWeatherState.value.copy(CurrentWeatherDataModel=result.data)
        }
    }

}