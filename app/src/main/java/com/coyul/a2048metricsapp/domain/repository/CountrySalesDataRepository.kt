package com.coyul.a2048metricsapp.domain.repository

import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import io.reactivex.rxjava3.core.Single

interface CountrySalesDataRepository {
    fun getCountrySalesData(accountId: Long): Single<List<CountrySalesData>>
}