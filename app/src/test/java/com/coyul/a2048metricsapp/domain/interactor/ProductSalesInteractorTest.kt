package com.coyul.a2048metricsapp.domain.interactor

import com.coyul.a2048metricsapp.domain.model.BreakDown
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.coyul.a2048metricsapp.domain.repository.ProductSalesRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import java.util.*

class ProductSalesInteractorTest {

    private val productSalesRepository: ProductSalesRepository = mockk()
    private val authInteractor: AuthInteractor = mockk()
    private val interactor: ProductSalesInteractor = ProductSalesInteractor(productSalesRepository, authInteractor)

    private val testError: Throwable = mockk()

    @Before
    fun setUp() {
        every { authInteractor.getAccountId() } returns TEST_ID
    }

    @Test
    fun `getSalesData positive test`() {
        val testData: SalesData = mockk()
        every { productSalesRepository.getSalesData(TEST_ID) } returns Single.just(testData)
        interactor.getSalesData()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertResult(testData)
    }

    @Test
    fun `getSalesData error test`() {
        every { productSalesRepository.getSalesData(TEST_ID) } returns Single.error(testError)
        interactor.getSalesData()
            .test()
            .assertError(testError)
    }

    @Test
    fun `getSalesData with period positive test`() {
        val testData: SalesData = mockk()
        val testDate: Date = mockk()
        every {
            productSalesRepository.getSalesData(
                TEST_ID,
                testDate,
                testDate
            )
        } returns Single.just(testData)
        interactor.getSalesData(Period(testDate, testDate))
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertResult(testData)
    }

    @Test
    fun `getSalesData with period error test`() {
        val testDate: Date = mockk()
        every {
            productSalesRepository.getSalesData(
                TEST_ID,
                testDate,
                testDate
            )
        } returns Single.error(testError)
        interactor.getSalesData(Period(testDate, testDate))
            .test()
            .assertError(testError)
    }

    @Test
    fun `getRangedSalesData positive test`() {
        val testList: List<SalesData> = mockk()
        every {
            productSalesRepository.getRangedSalesData(
                TEST_ID,
                BreakDown.DATE
            )
        } returns Single.just(testList)
        interactor.getRangedSalesData()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertResult(testList)
    }

    @Test
    fun `getRangedSalesData error test`() {
        every {
            productSalesRepository.getRangedSalesData(
                TEST_ID,
                BreakDown.DATE
            )
        } returns Single.error(testError)
        interactor.getRangedSalesData()
            .test()
            .assertError(testError)
    }

    @Test
    fun `getRangedSalesData with period positive test`() {
        val testList: List<SalesData> = mockk()
        val testDate: Date = mockk()
        every {
            productSalesRepository.getRangedSalesData(
                TEST_ID,
                testDate,
                testDate,
                BreakDown.DATE
            )
        } returns Single.just(testList)
        interactor.getRangedSalesData(Period(testDate, testDate))
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertResult(testList)
    }

    @Test
    fun `getRangedSalesData with period error test`() {
        val testDate: Date = mockk()
        every {
            productSalesRepository.getRangedSalesData(
                TEST_ID,
                testDate,
                testDate,
                BreakDown.DATE
            )
        } returns Single.error(testError)
        interactor.getRangedSalesData(Period(testDate, testDate))
            .test()
            .assertError(testError)
    }
}

private const val TEST_ID = 1000L