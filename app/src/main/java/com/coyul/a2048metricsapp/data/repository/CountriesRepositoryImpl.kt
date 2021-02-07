package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.domain.model.Country
import com.coyul.a2048metricsapp.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor() : CountriesRepository {
    override fun getCountries(): List<Country> {
        TODO("Not yet implemented")
    }
}