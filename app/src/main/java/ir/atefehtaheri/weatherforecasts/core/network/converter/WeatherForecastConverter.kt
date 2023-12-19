package ir.atefehtaheri.weatherforecasts.core.network.converter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.Item
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

class WeatherForecastConverter:Converter<ResponseBody,List<Item>> {
    override fun convert(value: ResponseBody): List<Item>? {
        val json = value.string()
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)

        val customResponseJson: JsonArray = jsonObject.getAsJsonArray("list")
        val listType: Type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(customResponseJson, listType)
    }
}