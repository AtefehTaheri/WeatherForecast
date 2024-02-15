package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.api.SearchCityApi
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto
import javax.inject.Inject

class SearchCityDataSourceImpl @Inject constructor(
    private val SearchCityApi: SearchCityApi

):SearchCityDataSource {

    override suspend fun getSuggestedList(city:String): ResultStatus<SuggestedListDto> {
        return when (val result = SearchCityApi.getSuggestedList(city)) {

            is NetworkResponse.Success -> {
                ResultStatus.Success(result.body)
            }
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(
                result.error.message ?: "NetworkError"
            )
            is NetworkResponse.UnknownError -> ResultStatus.Failure(
                result.error.message ?: "UnknownError"
            )
        }
    }
}