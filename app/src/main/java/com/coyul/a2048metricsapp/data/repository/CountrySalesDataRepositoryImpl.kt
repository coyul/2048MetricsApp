package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.data.api.CountriesApi
import com.coyul.a2048metricsapp.data.api.ProductSalesApi
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants
import com.coyul.a2048metricsapp.domain.converter.CountryProductSalesDataConverter
import com.coyul.a2048metricsapp.domain.model.BreakDown
import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import com.coyul.a2048metricsapp.domain.repository.CountrySalesDataRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CountrySalesDataRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi,
    private val productSalesApi: ProductSalesApi,
    private val converter: CountryProductSalesDataConverter) : CountrySalesDataRepository {

    override fun getCountrySalesData(accountId: Long): Single<List<CountrySalesData>> =
        Observable.zip(
            countriesApi.getCountries(),
            productSalesApi.getSalesData(accountId, BuildConfig.APP_ID, mapOf(ApiConstants.BREAKDOWN_QUERY to BreakDown.COUNTRY.value)),
            { rawCountries, rawSalesData -> converter.convert(Pair(rawCountries, rawSalesData)) })
            .firstOrError()
}