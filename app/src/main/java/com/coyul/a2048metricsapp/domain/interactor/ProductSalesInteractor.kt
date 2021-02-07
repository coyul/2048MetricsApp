package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.domain.model.SalesData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductSalesInteractor @Inject constructor() {
    fun getSalesData(): Single<SalesData> = TODO()
    fun getSalesData(period: Period): Single<SalesData> = TODO()
    fun getRangedSalesData(): Single<SalesData> = TODO()
    fun getRangedSalesData(period: Period): Single<SalesData> = TODO()
}