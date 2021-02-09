package com.coyul.a2048metricsapp.presentation.converter

import com.coyul.a2048metricsapp.base.DateConstants.TODAY_DAYS_AGO
import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.presentation.model.UiPeriod
import java.util.*
import javax.inject.Inject

class UiPeriodToDatePeriodConverter @Inject constructor() : OneWayConverter<UiPeriod, Period?> {

    override fun convert(item: UiPeriod): Period =
       Period(calculateDate(item.daysPeriod), calculateDate(TODAY_DAYS_AGO))

    private fun calculateDate(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calendar.time
    }
}