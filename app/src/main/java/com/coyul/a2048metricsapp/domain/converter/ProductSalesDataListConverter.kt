package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.Iap
import com.coyul.a2048metricsapp.data.model.Product
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import com.coyul.a2048metricsapp.domain.model.SalesData
import javax.inject.Inject

class ProductSalesDataListConverter @Inject constructor() :
    OneWayConverter<RawProductSalesData, List<SalesData>> {
    override fun convert(item: RawProductSalesData): List<SalesData> {

        val resultList: MutableList<SalesData> = mutableListOf()
        item.salesList.forEach {
            val product: Product = it.units.product
            val iap: Iap = it.revenue.iap
            resultList.add(
                SalesData(
                    (product.downloads + product.promotions).toLong(),
                    (iap.promotions + iap.refunds + iap.sales),
                    it.date,
                    it.country
                )
            )
        }
        return resultList.toList()
    }
}