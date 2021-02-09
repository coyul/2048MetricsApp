package com.coyul.a2048metricsapp.data.api

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.data.api.constant.ApiConstants
import com.coyul.a2048metricsapp.data.model.RawProductSalesData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductSalesApi {
    @Headers(ApiConstants.AUTHORIZATION_HEADER + BuildConfig.TOKEN)
    @GET("accounts/{accountId}/products/{productId}/sales")
    fun getSalesData(
        @Path("accountId") accountId: Long,
        @Path("productId") productId: Long = 1000L,
        @QueryMap queries: Map<String, String>
    ): Observable<RawProductSalesData>
}