package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class ProductSalesDataListConverter @Inject constructor(private val itemConverter: SaleItemConverter) :
    OneWayConverter<RawProductSalesData, List<SalesData>> {

    override fun convert(item: RawProductSalesData): List<SalesData> =
        item.salesList.map { itemConverter.convert(it) }
}