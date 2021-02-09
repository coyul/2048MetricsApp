package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.base.DateConstants.DATE_FORMAT_PATTERN
import com.coyul.a2048metricsapp.data.api.ProductSalesApi
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants
import com.coyul.a2048metricsapp.domain.converter.ProductSalesDataConverter
import com.coyul.a2048metricsapp.domain.converter.ProductSalesDataListConverter
import com.coyul.a2048metricsapp.domain.model.BreakDown
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.coyul.a2048metricsapp.domain.repository.ProductSalesRepository
import io.reactivex.rxjava3.core.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ProductSalesRepositoryImpl @Inject constructor(
    private val productSalesApi: ProductSalesApi,
    private val converter: ProductSalesDataConverter,
    private val listConverter: ProductSalesDataListConverter) : ProductSalesRepository {

    override fun getSalesData(accountId: Long): Single<SalesData> =
        productSalesApi.getSalesData(accountId, BuildConfig.APP_ID, emptyMap())
            .map { converter.convert(it) }
            .firstOrError()

    override fun getSalesData(
        accountId: Long,
        startDate: Date,
        endDate: Date): Single<SalesData> =
        productSalesApi.getSalesData(
            accountId,
            BuildConfig.APP_ID,
            mapOf(
                ApiConstants.STARTDATE_QUERY to getFormattedDateForQuery(startDate),
                ApiConstants.ENDDATE_QUERY to getFormattedDateForQuery(endDate)))
            .map { converter.convert(it) }
            .firstOrError()

    override fun getRangedSalesData(
        accountId: Long,
        breakDown: BreakDown): Single<List<SalesData>> =
        productSalesApi.getSalesData(accountId, BuildConfig.APP_ID, mapOf(ApiConstants.BREAKDOWN_QUERY to breakDown.value))
            .map { listConverter.convert(it) }
            .firstOrError()

    override fun getRangedSalesData(
        accountId: Long,
        startDate: Date,
        endDate: Date,
        breakDown: BreakDown): Single<List<SalesData>> =
        productSalesApi.getSalesData(
            accountId,
            BuildConfig.APP_ID,
            mapOf(
                ApiConstants.BREAKDOWN_QUERY to breakDown.value,
                ApiConstants.STARTDATE_QUERY to getFormattedDateForQuery(startDate),
                ApiConstants.ENDDATE_QUERY to getFormattedDateForQuery(endDate)))
            .map { listConverter.convert(it) }
            .firstOrError()

    private fun getFormattedDateForQuery(date: Date) : String =
                SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault()).format(date)
}