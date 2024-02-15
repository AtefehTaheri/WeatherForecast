package ir.atefehtaheri.weatherforecasts.core.network.di


import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.adapters.NetworkResponseCallAdapterFactory
import ir.atefehtaheri.weatherforecasts.core.network.converter.WeatherForecastConverterFactory
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton

    fun provideRetrofitWeather(
        networkResponseCallAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return provideRetrofit(
            BuildConfig.Base_Url,
            networkResponseCallAdapterFactory
        )
    }

    @Singleton
    @Provides
    fun provideNetworkResponseCallAdapterFactory(): CallAdapter.Factory {
        return NetworkResponseCallAdapterFactory.create()
    }


    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

}
fun provideRetrofit(
    baseUrl: String,
    networkResponseCallAdapterFactory: CallAdapter.Factory
): Retrofit {

    return Retrofit.Builder()

        .addConverterFactory(WeatherForecastConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(networkResponseCallAdapterFactory)
        .baseUrl(baseUrl)
        .build()
}
inline fun <reified T> createApiService(service: Class<T>, retrofit: Retrofit): T =
    retrofit.create(service)