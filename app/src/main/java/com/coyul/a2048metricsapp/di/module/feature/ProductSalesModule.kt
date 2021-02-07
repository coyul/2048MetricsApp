package com.coyul.a2048metricsapp.di.module.feature

import com.coyul.a2048metricsapp.data.repository.ProductSalesRepositoryImpl
import com.coyul.a2048metricsapp.data.repository.SharedProductsRepositoryImpl
import com.coyul.a2048metricsapp.domain.repository.ProductSalesRepository
import com.coyul.a2048metricsapp.domain.repository.SharedProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ProductSalesModule {

    @Binds
    abstract fun bindsProductSalesRepository(repository: ProductSalesRepositoryImpl): ProductSalesRepository

    @Binds
    abstract fun bindsSharedProductsRepository(repository: SharedProductsRepositoryImpl): SharedProductsRepository
}