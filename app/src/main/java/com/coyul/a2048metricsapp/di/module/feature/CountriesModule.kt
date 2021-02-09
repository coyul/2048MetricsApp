package com.coyul.a2048metricsapp.di.module.feature

import com.coyul.a2048metricsapp.data.repository.CountrySalesDataRepositoryImpl
import com.coyul.a2048metricsapp.domain.repository.CountrySalesDataRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CountriesModule {

    @Binds
    abstract fun bindsCountriesRepository(repository: CountrySalesDataRepositoryImpl): CountrySalesDataRepository
}