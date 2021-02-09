package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.repository.AuthRepository
import com.coyul.a2048metricsapp.domain.repository.SharedProductsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val sharedProductsRepository: SharedProductsRepository,
    private val authRepository: AuthRepository
) {
    fun loadAccountId(): Completable =
        sharedProductsRepository.getAccountId()
            .doOnSuccess { authRepository.saveAccountId(it) }
            .flatMapCompletable { Completable.complete() }

    fun getAccountId(): Long =
        authRepository.getSavedAccountId()
}