package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants.IMAGE_HOST
import com.coyul.a2048metricsapp.data.model.*
import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import java.util.*
import javax.inject.Inject

class CountryProductSalesDataConverter @Inject constructor() :
    OneWayConverter<Pair<RawCountries, RawProductSalesData>, List<CountrySalesData>> {

    override fun convert(item: Pair<RawCountries, RawProductSalesData>): List<CountrySalesData> {
        val countryList: List<Country> = item.first.countryList
        val resultList: MutableList<CountrySalesData> = mutableListOf()
        item.second.salesList.forEach { saleItem ->
            val product: Product = saleItem.units.product
            val iap: Iap = saleItem.revenue.iap
            val countryCode: String = saleItem.country
            resultList.add(
                CountrySalesData(
                    (product.downloads + product.promotions).toLong(),
                    (iap.promotions + iap.refunds + iap.sales),
                    countryCode,
                    countryList.find { it.countryCode == countryCode }?.countryName,
                    "$IMAGE_HOST${countryCode.toLowerCase(Locale.getDefault())}$EXTENSION"
                )
            )
        }
        return resultList.toList()
    }

    companion object {
        private const val EXTENSION: String = ".gif"
    }
}