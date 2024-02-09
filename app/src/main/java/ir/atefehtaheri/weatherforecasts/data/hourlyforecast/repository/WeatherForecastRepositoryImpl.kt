package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository

//import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.WeatherForecastDataSource
import ir.atefehtaheri.weatherforecasts.core.common.models.ResultStatus
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.WeatherForecastDataSource
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.model.toWeatherForecastDataModel
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.models.ListWeatherForecastDataModel
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val WeatherForecastDataSource: WeatherForecastDataSource
) : WeatherForecastRepository {
    override suspend fun getListWeatherForecast(city: String):
            ResultStatus<ListWeatherForecastDataModel> {

        return when (val result = WeatherForecastDataSource.getHourlyForecast(city)) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> {
                val list = ListWeatherForecastDataModel(result.data!!.map { it.toWeatherForecastDataModel()})
                ResultStatus.Success(list)
            }
        }
    }
}