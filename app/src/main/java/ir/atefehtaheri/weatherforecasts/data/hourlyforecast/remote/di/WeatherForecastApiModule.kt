package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.core.network.di.createApiService
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.api.WeatherForecastApi
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object WeatherForecastApiModule {

    @Provides
    fun getHourlyForecastApiModule(retrofit: Retrofit): WeatherForecastApi {
        return createApiService(WeatherForecastApi::class.java, retrofit)
    }

}