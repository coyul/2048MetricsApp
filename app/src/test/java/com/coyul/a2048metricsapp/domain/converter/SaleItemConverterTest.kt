package com.coyul.a2048metricsapp.domain.converter

import com.coyul.a2048metricsapp.data.model.*
import com.coyul.a2048metricsapp.domain.model.SalesData
import org.junit.Assert.assertEquals
import org.junit.Test

class SaleItemConverterTest {

    private val converter: SaleItemConverter = SaleItemConverter()

    @Test
    fun convertTest() {
        val salesToConvert = Sales(
            TEST_COUNTRY, TEST_DATE,
            Revenue("", Iap(1.1, 2.2, 3.3), Product(0.0, 0.0, 0.0, 0.0)),
            Units(Iap(0.0, 0.0, 0.0), Product(4.0, 5.0, 6.6, 7.7))
        )
        val expectedSalesData = SalesData(9, 6.6, TEST_DATE, TEST_COUNTRY)
        assertEquals(expectedSalesData, converter.convert(salesToConvert))
    }
}

private const val TEST_COUNTRY = "NL"
private const val TEST_DATE = "2019-03-30"