package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.repository.AuthRepository
import com.coyul.a2048metricsapp.domain.repository.SharedProductsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Test


class AuthInteractorTest {

    private val sharedProductsRepository: SharedProductsRepository = mockk()
    private val authRepository: AuthRepository = mockk()
    private val interactor: AuthInteractor = AuthInteractor(sharedProductsRepository, authRepository)

    @Test
    fun `loadAccountId positive test`() {
        val id = 1000L
        every { sharedProductsRepository.getAccountId() } returns Single.just(id)
        every { authRepository.saveAccountId(id) } returns Unit
        interactor.loadAccountId()
            .test()
            .assertComplete()
        verify { authRepository.saveAccountId(id) }
    }

    @Test
    fun `getAccountId positive test`() {
        val id = 1000L
        every { authRepository.getSavedAccountId() } returns id
        assertEquals(id, interactor.getAccountId())
    }
}