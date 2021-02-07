package com.coyul.a2048metricsapp.domain.repository

interface AuthRepository {
    fun getSavedAccountId(): String
    fun saveAccountId(): String
}