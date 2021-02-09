package com.coyul.a2048metricsapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coyul.a2048metricsapp.domain.interactor.CountriesInteractor
import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifySequence
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountriesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val countriesInteractor: CountriesInteractor = mockk()
    private lateinit var viewModel: CountriesViewModel

    private val listObserver: Observer<List<CountrySalesData>> = spyk()
    private val progressObserver: Observer<Boolean> = spyk()
    private val errorObserver: Observer<Unit> = spyk()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = CountriesViewModel(countriesInteractor)
        viewModel.listLiveData.observeForever(listObserver)
        viewModel.progressLiveData.observeForever(progressObserver)
        viewModel.errorLiveData.observeForever(errorObserver)
    }

    @Test
    fun `load positive test`() {
        val testList: List<CountrySalesData> = mockk()
        every { countriesInteractor.getCountrySalesData() } returns Single.just(testList)
        viewModel.load()
        verifySequence {
            progressObserver.onChanged(true)
            listObserver.onChanged(testList)
            progressObserver.onChanged(false)
        }
    }

    @Test
    fun `load error test`() {
        val testError: Throwable = mockk()
        every { countriesInteractor.getCountrySalesData() } returns Single.error(testError)
        viewModel.load()
        verifySequence {
            progressObserver.onChanged(true)
            errorObserver.onChanged(Unit)
            progressObserver.onChanged(false)
        }
    }
}