package ir.atefehtaheri.weatherforecasts.data.SearchCity.repository

import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto

interface SearchCityRepository {

    suspend fun getSuggestedList(city:String): ResultStatus<SuggestedListDto>
}