package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.data.model.RawCountries
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CountriesApi {
    @GET(".")
    fun getCountries(): Observable<RawCountries>
}