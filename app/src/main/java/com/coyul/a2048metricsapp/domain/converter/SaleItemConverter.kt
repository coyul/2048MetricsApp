package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.Iap
import com.coyul.a2048metricsapp.data.model.Product
import com.coyul.a2048metricsapp.data.model.Sales
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class SaleItemConverter @Inject constructor() : OneWayConverter<Sales, SalesData> {

    override fun convert(item: Sales): SalesData {
        val product: Product = item.units.product
        val iap: Iap = item.revenue.iap
        return SalesData(
            (product.downloads + product.promotions).toLong(),
            (iap.promotions + iap.refunds + iap.sales),
            item.date,
            item.country
        )
    }
}