package ir.atefehtaheri.weatherforecasts.feature.SearchCity.UiState

import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.models.SuggestedListDto

data class SuggestedListState (
    val isLoading:Boolean=false,
    val SuggestedItems:SuggestedListDto?=null,
    val error:String?= null,
)