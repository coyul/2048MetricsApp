package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.model.BreakDown
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.coyul.a2048metricsapp.domain.repository.ProductSalesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductSalesInteractor @Inject constructor(
    private val productSalesRepository: ProductSalesRepository,
    private val authInteractor: AuthInteractor) {

    fun getSalesData(): Single<SalesData> =
        productSalesRepository.getSalesData(authInteractor.getAccountId())

    fun getSalesData(period: Period): Single<SalesData> =
        productSalesRepository.getSalesData(
            authInteractor.getAccountId(),
            period.startDate, period.endDate
        )

    fun getRangedSalesData(): Single<List<SalesData>> =
        productSalesRepository.getRangedSalesData(
            authInteractor.getAccountId(),
            BreakDown.DATE
        )

    fun getRangedSalesData(period: Period): Single<List<SalesData>> =
        productSalesRepository.getRangedSalesData(
            authInteractor.getAccountId(),
            period.startDate, period.endDate,
            BreakDown.DATE
        )
}