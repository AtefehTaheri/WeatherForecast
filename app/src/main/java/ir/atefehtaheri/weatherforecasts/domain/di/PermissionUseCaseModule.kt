package ir.atefehtaheri.weatherforecasts.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepository
import ir.atefehtaheri.weatherforecasts.data.hourlyforecast.repository.WeatherForecastRepositoryImpl
import ir.atefehtaheri.weatherforecasts.domain.usecase.CheckGps
import ir.atefehtaheri.weatherforecasts.domain.usecase.CheckPermission
import ir.atefehtaheri.weatherforecasts.domain.usecase.EnableGps
import ir.atefehtaheri.weatherforecasts.domain.usecase.PermissionUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PermissionUseCaseModule {

    @Provides
    @Singleton
    fun getPermissionUseCase(): PermissionUseCase{
        return PermissionUseCase(CheckGps(), CheckPermission(), EnableGps())
    }
}