package com.coyul.a2048metricsapp.data.repository

import com.coyul.a2048metricsapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private var accountId: Long = 0L

    override fun saveAccountId(accountId: Long) {
        this.accountId = accountId
    }

    override fun getSavedAccountId(): Long = accountId
}