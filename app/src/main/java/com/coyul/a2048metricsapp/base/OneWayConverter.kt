package com.coyul.a2048metricsapp.base

interface OneWayConverter<From, To> {
    fun convert(item: From): To
}