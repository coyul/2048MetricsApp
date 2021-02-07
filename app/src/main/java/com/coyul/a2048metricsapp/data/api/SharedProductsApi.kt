package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.data.model.RawSharedProductsData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface SharedProductsApi {

    @GET(".")
    fun getSharedProducts(): Observable<RawSharedProductsData>
}