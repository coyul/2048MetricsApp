package com.coyul.a2048metricsapp.domain.model

data class CountrySalesData(
    val downloads: Long,
    val revenue: Double,
    val countryCode: String,
    val countryName: String?,
    val countryImageUrl: String
)