package ir.atefehtaheri.weatherforecasts.data.SearchCity.repository

import android.util.Log
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.SearchCityDataSource
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.models.toCurrentWeatherDataModel
import javax.inject.Inject

class SearchCityRepositoryImpl @Inject constructor(
    private val SearchCityDataSource: SearchCityDataSource
):SearchCityRepository {
    override suspend fun getSuggestedList(city: String): ResultStatus<SuggestedListDto> {

        return when (val result = SearchCityDataSource.getSuggestedList(city)) {

            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> {
                ResultStatus.Success(result.data)}
        }
    }
}