package com.coyul.a2048metricsapp.domain.repository

interface AuthRepository {
    fun getSavedAccountId(): Long
    fun saveAccountId(accountId: Long)
}