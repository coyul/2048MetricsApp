package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.data.api.SharedProductsApi
import com.coyul.a2048metricsapp.data.model.RawSharedProductsData
import com.coyul.a2048metricsapp.domain.converter.SharedProductsToAccountIdConverter
import com.coyul.a2048metricsapp.domain.repository.SharedProductsRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class SharedProductsRepositoryImplTest {

    private val api: SharedProductsApi = mockk()
    private val converter: SharedProductsToAccountIdConverter = mockk()
    private val repository: SharedProductsRepository = SharedProductsRepositoryImpl(api, converter)

    @Test
    fun `getAccountId positive result test`() {
        val rawData: RawSharedProductsData = mockk()
        val id = 1000L
        every { api.getSharedProducts() } returns Observable.just(rawData)
        every { converter.convert(rawData) } returns id
        repository.getAccountId()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertResult(id)
    }

    @Test
    fun `getAccountId error result test`() {
        val testError: Throwable = mockk()
        every { api.getSharedProducts() } returns Observable.error(testError)
        repository.getAccountId()
            .test()
            .assertError(testError)
    }
}