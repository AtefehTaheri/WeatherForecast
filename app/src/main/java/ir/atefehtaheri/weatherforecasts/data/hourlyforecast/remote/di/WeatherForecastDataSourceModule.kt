package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.WeatherForecastDataSource
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.remote.WeatherForecastDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherForecastDataSourceModule {
    @Singleton
    @Binds
    fun getWeatherForecastDataSourceModule(WeatherForecastDataSourceImpl: WeatherForecastDataSourceImpl): WeatherForecastDataSource
}