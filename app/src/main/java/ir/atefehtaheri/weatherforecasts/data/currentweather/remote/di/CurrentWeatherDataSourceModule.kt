package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.CurrentWeatherDataSource
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.CurrentWeatherDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrentWeatherDataSourceModule {

    @Singleton
    @Binds
    fun getCurrentWeatherDataSourceModul(
        CurrentWeatherDataSourceImpl: CurrentWeatherDataSourceImpl
    ): CurrentWeatherDataSource
}