package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants.AUTHORIZATION_HEADER
import com.coyul.a2048metricsapp.data.model.RawSharedProductsData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface SharedProductsApi {
    @Headers(AUTHORIZATION_HEADER + BuildConfig.TOKEN)
    @GET("sharing/products")
    fun getSharedProducts(): Observable<RawSharedProductsData>
}