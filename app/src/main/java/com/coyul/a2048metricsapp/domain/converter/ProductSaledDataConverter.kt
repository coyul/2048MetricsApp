package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class ProductSaledDataConverter @Inject constructor() :
    OneWayConverter<RawProductSalesData, SalesData> {
    override fun convert(item: RawProductSalesData): SalesData {
        TODO("Not yet implemented")
    }
}