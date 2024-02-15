package ir.atefehtaheri.weatherforecasts.data.SearchCity.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.weatherforecasts.data.SearchCity.repository.SearchCityRepository
import ir.atefehtaheri.weatherforecasts.data.SearchCity.repository.SearchCityRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchCiryRepositoryModule {
    @Binds
    @Singleton
    fun getSearchCiryRepository(
        SearchCityRepositoryImpl: SearchCityRepositoryImpl
    ): SearchCityRepository



}