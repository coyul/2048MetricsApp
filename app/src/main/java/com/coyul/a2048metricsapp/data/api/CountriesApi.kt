package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants
import com.coyul.a2048metricsapp.data.model.RawCountries
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountriesApi {
    @Headers(ApiConstants.AUTHORIZATION_HEADER + BuildConfig.TOKEN)
    @GET("meta/countries")
    fun getCountries(): Observable<RawCountries>
}