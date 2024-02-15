package ir.atefehtaheri.weatherforecasts.core.network

import okhttp3.Headers
import java.io.IOException

sealed class NetworkResponse<out S,out E>{

        /**
         * Success response
         */
        data class Success<S : Any>(val body: S? = null, val headers: Headers? = null) :
            NetworkResponse<S, Nothing>()

        /**
         * Failure response with body
         */
        data class ApiError<E : Any>(val body: E, val code: Int) : NetworkResponse<Nothing, E>()

        /**
         * Network error
         */
        data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

        /**
         * For example, json parsing error
         */
        data class UnknownError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()


}
