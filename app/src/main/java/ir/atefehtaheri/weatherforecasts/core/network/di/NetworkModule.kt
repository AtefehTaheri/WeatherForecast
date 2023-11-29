package ir.atefehtaheri.weatherforecasts.core.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.BuildConfig
import ir.atefehtaheri.weatherforecasts.core.network.adapters.NetworkResponseCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitCurrentWeather(
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

}
fun provideRetrofit(
    baseUrl: String,
    networkResponseCallAdapterFactory: CallAdapter.Factory
): Retrofit {

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(networkResponseCallAdapterFactory)
        .baseUrl(baseUrl)
        .build()
}
inline fun <reified T> createApiService(service: Class<T>, retrofit: Retrofit): T =
    retrofit.create(service)