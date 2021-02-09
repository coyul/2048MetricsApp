package com.coyul.a2048metricsapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coyul.a2048metricsapp.domain.interactor.AuthInteractor
import com.coyul.a2048metricsapp.domain.interactor.ProductSalesInteractor
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.coyul.a2048metricsapp.presentation.converter.SalesDataToBarConverter
import com.coyul.a2048metricsapp.presentation.converter.UiPeriodToDatePeriodConverter
import com.coyul.a2048metricsapp.presentation.model.UiPeriod
import com.github.mikephil.charting.data.BarDataSet
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifySequence
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainMetricsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor: ProductSalesInteractor = mockk()
    private val authInteractor: AuthInteractor = mockk()
    private val periodConverter: UiPeriodToDatePeriodConverter = mockk()
    private val barConverter: SalesDataToBarConverter = mockk()
    private lateinit var viewModel: MainMetricsViewModel

    private val salesDataObserver: Observer<SalesData> = spyk()
    private val salesDataBarSetObserver: Observer<Pair<BarDataSet, BarDataSet>> = spyk()
    private val progressTextObserver: Observer<Boolean> = spyk()
    private val progressBarObserver: Observer<Boolean> = spyk()
    private val errorLiveObserver: Observer<Unit> = spyk()

    private val testData: SalesData = mockk()
    private val testList: List<SalesData> = mockk()
    private val testBarPair: Pair<BarDataSet, BarDataSet> = mockk()
    private val testError: Throwable = mockk()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = MainMetricsViewModel(interactor, authInteractor, periodConverter, barConverter)
        viewModel.salesDataLiveData.observeForever(salesDataObserver)
        viewModel.salesDataBarSetLiveData.observeForever(salesDataBarSetObserver)
        viewModel.progressTextLiveData.observeForever(progressTextObserver)
        viewModel.progressBarLiveData.observeForever(progressBarObserver)
        viewModel.errorLiveData.observeForever(errorLiveObserver)
    }

    @Test
    fun `firstLoad positive test`() {
        every { authInteractor.loadAccountId() } returns Completable.complete()
        every { interactor.getSalesData() } returns Single.just(testData)
        every { interactor.getRangedSalesData() } returns Single.just(testList)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.firstLoad()
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            salesDataObserver.onChanged(testData)
            progressTextObserver.onChanged(false)
            salesDataBarSetObserver.onChanged(testBarPair)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `firstLoad error in getSalesData test`() {
        every { authInteractor.loadAccountId() } returns Completable.complete()
        every { interactor.getSalesData() } returns Single.error(testError)
        every { interactor.getRangedSalesData() } returns Single.just(testList)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.firstLoad()
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            errorLiveObserver.onChanged(Unit)
            progressTextObserver.onChanged(false)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `firstLoad error in getRangedSalesData test`() {
        every { authInteractor.loadAccountId() } returns Completable.complete()
        every { interactor.getSalesData() } returns Single.just(testData)
        every { interactor.getRangedSalesData() } returns Single.error(testError)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.firstLoad()
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            progressTextObserver.onChanged(false)
            errorLiveObserver.onChanged(Unit)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `firstLoad error in getSalesData and getRangedSalesData test`() {
        every { authInteractor.loadAccountId() } returns Completable.complete()
        every { interactor.getSalesData() } returns Single.error(testError)
        every { interactor.getRangedSalesData() } returns Single.error(testError)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.firstLoad()
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            errorLiveObserver.onChanged(Unit)
            progressTextObserver.onChanged(false)
            errorLiveObserver.onChanged(Unit)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `onDateFilterClicked position 0 positive test`() {
        every { interactor.getSalesData() } returns Single.just(testData)
        every { interactor.getRangedSalesData() } returns Single.just(testList)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.onDateFilterClicked(0)
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            salesDataObserver.onChanged(testData)
            progressTextObserver.onChanged(false)
            salesDataBarSetObserver.onChanged(testBarPair)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `onDateFilterClicked position more then 0 positive test`() {
        val period: Period = mockk()
        every { periodConverter.convert(UiPeriod.ONE_WEEK) } returns period
        every { interactor.getSalesData(period) } returns Single.just(testData)
        every { interactor.getRangedSalesData(period) } returns Single.just(testList)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.onDateFilterClicked(1)
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            salesDataObserver.onChanged(testData)
            progressTextObserver.onChanged(false)
            salesDataBarSetObserver.onChanged(testBarPair)
            progressBarObserver.onChanged(false)
        }
    }

    @Test
    fun `onDateFilterClicked error test`() {
        val period: Period = mockk()
        every { periodConverter.convert(UiPeriod.ONE_WEEK) } returns period
        every { interactor.getSalesData(period) } returns Single.error(testError)
        every { interactor.getRangedSalesData(period) } returns Single.just(testList)
        every { barConverter.convert(testList) } returns testBarPair
        viewModel.onDateFilterClicked(1)
        verifySequence {
            progressTextObserver.onChanged(true)
            progressBarObserver.onChanged(true)
            errorLiveObserver.onChanged(Unit)
            progressTextObserver.onChanged(false)
            progressBarObserver.onChanged(false)
        }
    }
}