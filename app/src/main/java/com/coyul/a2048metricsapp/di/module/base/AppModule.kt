package com.coyul.a2048metricsapp.di.module.base

import com.coyul.a2048metricsapp.data.repository.AuthRepositoryImpl
import com.coyul.a2048metricsapp.di.scope.ApplicationScope
import com.coyul.a2048metricsapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @ApplicationScope
    abstract fun bindsProductsLifeCycleRepository(repository: AuthRepositoryImpl): AuthRepository
}