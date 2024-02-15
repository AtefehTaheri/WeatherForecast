package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.core.network.di.createApiService
import ir.atefehtaheri.weatherforecasts.data.currentweather.remote.api.CurrentWeatherApi
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrentWeather
@Module
@InstallIn(SingletonComponent::class)
object CurrentWeatherApiModule {

    @Provides
    @Singleton
    fun getCurrentWeatherApiModule(retrofit: Retrofit): CurrentWeatherApi {
        return createApiService(CurrentWeatherApi::class.java, retrofit)
    }

}