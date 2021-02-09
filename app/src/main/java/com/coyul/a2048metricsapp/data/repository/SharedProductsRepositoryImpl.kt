package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.data.api.SharedProductsApi
import com.coyul.a2048metricsapp.domain.converter.SharedProductsToAccountIdConverter
import com.coyul.a2048metricsapp.domain.repository.SharedProductsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SharedProductsRepositoryImpl @Inject constructor(
    private val sharedProductsApi: SharedProductsApi,
    private val converter: SharedProductsToAccountIdConverter
) : SharedProductsRepository {

    override fun getAccountId(): Single<Long> =
        sharedProductsApi.getSharedProducts()
            .map { converter.convert(it) }
            .singleOrError()
}