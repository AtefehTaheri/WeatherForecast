package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.core.network.di.createApiService
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.api.SearchCityApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SearchCityApiModule {

    @Provides
    fun getSearchCityApiModule(retrofit: Retrofit): SearchCityApi {
        return createApiService(SearchCityApi::class.java, retrofit)
    }

}