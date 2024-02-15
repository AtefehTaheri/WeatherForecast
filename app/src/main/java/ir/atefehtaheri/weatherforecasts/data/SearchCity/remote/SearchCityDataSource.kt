package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto

interface SearchCityDataSource {
    suspend fun getSuggestedList(city:String):ResultStatus<SuggestedListDto>
}