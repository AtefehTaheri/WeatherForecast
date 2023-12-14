package ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepository
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherForecastRepositoryModule {
    @Binds
    @Singleton
    fun getWeatherForecastRepository(
        WeatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository
}