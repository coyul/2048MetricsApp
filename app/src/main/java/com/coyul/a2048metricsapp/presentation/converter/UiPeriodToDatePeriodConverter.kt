package com.coyul.a2048metricsapp.presentation.converter

import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.presentation.model.UiPeriod
import javax.inject.Inject

class UiPeriodToDatePeriodConverter @Inject constructor() : OneWayConverter<UiPeriod, Period> {
    override fun convert(item: UiPeriod): Period {
        TODO("Not yet implemented")
    }
}