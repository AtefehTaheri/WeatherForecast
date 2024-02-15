package ir.atefehtaheri.weatherforecasts.core.common.models

sealed class ResultStatus<out S>{
    data class Success<S>(val data: S?) : ResultStatus<S>()
    data class Failure(val exception_message: String) : ResultStatus<Nothing>()
}
