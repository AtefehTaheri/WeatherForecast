package ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.SearchCityDataSource
import ir.atefehtaheri.weatherforecasts.data.SearchCity.remote.SearchCityDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchCityDataSourceModule {

    @Binds
    @Singleton
    fun getSearchCityDataSource(SearchCityDataSourceImpl: SearchCityDataSourceImpl): SearchCityDataSource


}