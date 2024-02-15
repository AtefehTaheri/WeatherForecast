package ir.atefehtaheri.weatherforecasts.data.currentweather.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.CurrentWeatherRepository
import ir.atefehtaheri.weatherforecasts.data.currentweather.repository.CurrentWeatherRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrentWeatherRepositoryModule {
    @Singleton
    @Binds
    fun getCurrentWeatherRepository(
        currentWeatherRepositoryImpl: CurrentWeatherRepositoryImpl
    ): CurrentWeatherRepository


}