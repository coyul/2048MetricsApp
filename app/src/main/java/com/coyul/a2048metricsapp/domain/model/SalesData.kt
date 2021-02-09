package com.coyul.a2048metricsapp.domain.model

data class SalesData(
    val downloads: Long,
    val revenue: Double,
    val date: String,
    val countryCode: String
)
