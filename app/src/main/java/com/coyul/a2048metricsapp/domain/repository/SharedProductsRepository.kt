package com.coyul.a2048metricsapp.domain.repository

import io.reactivex.rxjava3.core.Single

interface SharedProductsRepository {
    fun getAccountId(): Single<Long>
}