package com.coyul.a2048metricsapp.domain.repository

import com.coyul.a2048metricsapp.domain.model.Country

interface CountriesRepository {
    fun getCountries(): List<Country>
}