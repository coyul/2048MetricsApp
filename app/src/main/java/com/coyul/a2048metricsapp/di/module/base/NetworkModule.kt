package com.coyul.a2048metricsapp.di.module.base

import com.coyul.a2048metricsapp.BuildConfig
import com.coyul.a2048metricsapp.data.api.CountriesApi
import com.coyul.a2048metricsapp.data.api.ProductSalesApi
import com.coyul.a2048metricsapp.data.api.SharedProductsApi
import com.coyul.a2048metricsapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideProductSalesApi(retrofit: Retrofit): ProductSalesApi {
        return retrofit.create(ProductSalesApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideSharedProductsApi(retrofit: Retrofit): SharedProductsApi {
        return retrofit.create(SharedProductsApi::class.java)
    }
}