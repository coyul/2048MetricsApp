package com.coyul.a2048metricsapp.domain.repository

import com.coyul.a2048metricsapp.domain.model.BreakDown
import com.coyul.a2048metricsapp.domain.model.SalesData
import io.reactivex.rxjava3.core.Single
import java.util.*

interface ProductSalesRepository {
    fun getSalesData(accountId: Long): Single<SalesData>
    fun getSalesData(accountId: Long, startDate: Date, endDate: Date): Single<SalesData>
    fun getRangedSalesData(accountId: Long, breakDown: BreakDown): Single<List<SalesData>>
    fun getRangedSalesData(accountId: Long, startDate: Date, endDate: Date, breakDown: BreakDown): Single<List<SalesData>>
}