package ir.atefehtaheri.weatherforecasts.core.network.converter

import android.util.Log
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.Item
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class WeatherForecastConverterFactory:Converter.Factory() {


    companion object {
        fun create(): WeatherForecastConverterFactory {
            return WeatherForecastConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        if (type is ParameterizedType && type.rawType == List::class.java) {
            val elementType = type.actualTypeArguments[0]
            if (elementType == Item::class.java) {
                return WeatherForecastConverter()
            }
        }
        return null
    }
}