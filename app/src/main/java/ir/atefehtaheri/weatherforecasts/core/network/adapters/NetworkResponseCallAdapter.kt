package ir.atefehtaheri.weatherforecasts.core.network.adapters

import ir.atefehtaheri.weatherforecasts.core.network.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

internal class NetworkResponseCallAdapter<S:Any,E:Any> (
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
):CallAdapter<S, Call<NetworkResponse<S, E>>> {
    override fun responseType(): Type =successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(call,errorBodyConverter)
    }
}