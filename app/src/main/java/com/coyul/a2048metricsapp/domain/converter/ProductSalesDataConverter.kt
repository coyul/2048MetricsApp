package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class ProductSalesDataConverter @Inject constructor(private val itemConverter: SaleItemConverter) :
    OneWayConverter<RawProductSalesData, SalesData> {

    override fun convert(item: RawProductSalesData): SalesData {
        return if (item.salesList.isNotEmpty()) itemConverter.convert(item.salesList.first())
        else SalesData(
            DEFAULT_DOWNLOADS,
            DEFAULT_REVENUE,
            DEFAULT_DATE,
            DEFAULT_COUNTRY
        )
    }

    companion object {
        private const val DEFAULT_DOWNLOADS: Long = 0L
        private const val DEFAULT_REVENUE: Double = 0.0
        private const val DEFAULT_DATE: String = ""
        private const val DEFAULT_COUNTRY: String = "US"
    }
}