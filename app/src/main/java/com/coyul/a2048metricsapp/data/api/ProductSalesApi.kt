package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ProductSalesApi {

    @GET(".")
    fun getSalesData(): Observable<RawProductSalesData>
}