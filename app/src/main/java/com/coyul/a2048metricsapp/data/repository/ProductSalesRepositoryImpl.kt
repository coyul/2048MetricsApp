package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.data.api.ProductSalesApi
import com.coyul.a2048metricsapp.domain.repository.ProductSalesRepository
import javax.inject.Inject

class ProductSalesRepositoryImpl @Inject constructor(
    private val productSalesApi: ProductSalesApi
) : ProductSalesRepository {
}