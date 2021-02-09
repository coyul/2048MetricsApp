package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import com.coyul.a2048metricsapp.domain.repository.CountrySalesDataRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CountriesInteractor @Inject constructor(
   private val authInteractor: AuthInteractor,
    private val countrySalesDataRepository: CountrySalesDataRepository) {

    fun getCountrySalesData(): Single<List<CountrySalesData>> =
        countrySalesDataRepository.getCountrySalesData(authInteractor.getAccountId())
}