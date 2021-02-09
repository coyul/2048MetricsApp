package com.coyul.a2048metricsapp.presentation.converter

import com.coyul.a2048metricsapp.base.DateConstants.DATE_FORMAT_PATTERN
import com.coyul.a2048metricsapp.base.DateConstants.SECONDS_IN_MINUTE
import com.coyul.a2048metricsapp.base.OneWayConverter
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class SalesDataToBarConverter @Inject constructor() : OneWayConverter<List<SalesData>, Pair<BarDataSet, BarDataSet>> {

    override fun convert(item: List<SalesData>): Pair<BarDataSet, BarDataSet> {
        val downloadsEntries: MutableList<BarEntry> = mutableListOf()
        val revenueEntries: MutableList<BarEntry> = mutableListOf()
        item.forEach { dataItem ->
            val date: Float =  SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault()).parse(dataItem.date).time.toFloat() / SECONDS_IN_MINUTE
            downloadsEntries.add(BarEntry(date, dataItem.downloads.toFloat()))
            revenueEntries.add(BarEntry(date, dataItem.revenue.toFloat()))
        }
        return Pair(
            BarDataSet(downloadsEntries, "downloadsBarDataSet"),
            BarDataSet(revenueEntries, "revenuesBarDataSet")
        )
    }
}