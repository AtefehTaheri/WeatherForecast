package ir.atefehtaheri.weatherforecasts.data.LocationManager.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStore
import ir.atefehtaheri.weatherforecasts.data.LocationManager.LocationDataStoreImpl
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepository
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocationDataStoreModule {

    @Binds
    @Singleton
    fun getLocationDataStore(
        LocationDataStoreImpl: LocationDataStoreImpl
    ): LocationDataStore

}