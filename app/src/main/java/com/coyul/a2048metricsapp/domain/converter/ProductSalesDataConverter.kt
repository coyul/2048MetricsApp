package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.Iap
import com.coyul.a2048metricsapp.data.model.Product
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import com.coyul.a2048metricsapp.data.model.Sales
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class ProductSalesDataConverter @Inject constructor() :
    OneWayConverter<RawProductSalesData, SalesData> {
    override fun convert(item: RawProductSalesData): SalesData {

        val firstSaleItem: Sales = item.salesList.first()
        val product: Product = firstSaleItem.units.product
        val iap: Iap = firstSaleItem.revenue.iap
        return SalesData(
            (product.downloads + product.promotions).toLong(),
            (iap.promotions + iap.refunds + iap.sales),
            firstSaleItem.date,
            firstSaleItem.country
        )
    }
}