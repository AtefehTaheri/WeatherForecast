package ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepository
import ir.atefehtaheri.weatherforecasts.data.currentLocation.repository.CurrentLocationRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface CurrentLocationRepositoryModule {
    @Binds
    @Singleton
    fun getCurrentLocationRepository(
        CurrentLocationRepositoryImpl: CurrentLocationRepositoryImpl
    ): CurrentLocationRepository

}