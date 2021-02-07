package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.data.model.RawSharedProductsData
import javax.inject.Inject

class SharedProductsToAccountIdConverter @Inject constructor() :
    OneWayConverter<RawSharedProductsData, String> {
    override fun convert(item: RawSharedProductsData): String {
        TODO("Not yet implemented")
    }
}