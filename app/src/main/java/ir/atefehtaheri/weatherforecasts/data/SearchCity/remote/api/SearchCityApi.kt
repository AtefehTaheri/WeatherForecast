package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.api

import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.ErrorResponse
import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityApi {
    @GET("/geo/1.0/direct?")
    suspend fun getSuggestedList(
        @Query("q") city: String,
        @Query("appid") key: String = BuildConfig.API_KEY,
        @Query("limit") limit: Int = 5

    ): NetworkResponse<SuggestedListDto, ErrorResponse>


}