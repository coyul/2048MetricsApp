package com.coyul.a2048metricsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coyul.a2048metricsapp.domain.interactor.AuthInteractor
import com.coyul.a2048metricsapp.domain.interactor.ProductSalesInteractor
import com.coyul.a2048metricsapp.domain.model.Period
import com.coyul.a2048metricsapp.domain.model.SalesData
import com.coyul.a2048metricsapp.presentation.converter.SalesDataToBarConverter
import com.coyul.a2048metricsapp.presentation.converter.UiPeriodToDatePeriodConverter
import com.coyul.a2048metricsapp.presentation.model.UiPeriod
import com.github.mikephil.charting.data.BarDataSet
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainMetricsViewModel @Inject constructor(
    private val interactor: ProductSalesInteractor,
    private val authInteractor: AuthInteractor,
    private val periodConverter: UiPeriodToDatePeriodConverter,
    private val barConverter: SalesDataToBarConverter
) : ViewModel() {

    private val _salesDataLiveData = MutableLiveData<SalesData>()
    private val _salesDataBarSetLiveData: MutableLiveData<Pair<BarDataSet, BarDataSet>> =
        MutableLiveData()
    private val _progressTextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _errorLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val disposable = CompositeDisposable()
    val salesDataLiveData: LiveData<SalesData> = _salesDataLiveData
    val salesDataBarSetLiveData: LiveData<Pair<BarDataSet, BarDataSet>> = _salesDataBarSetLiveData
    val progressTextLiveData: LiveData<Boolean> = _progressTextLiveData
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData
    val errorLiveData: LiveData<Unit> = _errorLiveData

    fun firstLoad() {
        _progressTextLiveData.postValue(true)
        _progressBarLiveData.postValue(true)
        authInteractor.loadAccountId().blockingAwait()
        disposable.addAll(
            interactor.getSalesData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally {
                    _progressTextLiveData.postValue(false)
                }
                .subscribe(
                    { _salesDataLiveData.postValue(it) },
                    { _errorLiveData.postValue(Unit) }
                ))
        disposable.add(
            interactor.getRangedSalesData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { barConverter.convert(it) }
                .doFinally { _progressBarLiveData.postValue(false) }
                .subscribe(
                    { _salesDataBarSetLiveData.postValue(it) },
                    { _errorLiveData.postValue(Unit) }
                ))
    }

    fun loadMetrics(period: UiPeriod) {
        val datePeriod: Period = periodConverter.convert(period)
        disposable.add(
            interactor.getSalesData(datePeriod)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally { _progressTextLiveData.postValue(false) }
                .subscribe(
                    { _salesDataLiveData.postValue(it) },
                    { _errorLiveData.postValue(Unit) }
                ))
        disposable.add(
            interactor.getRangedSalesData(datePeriod)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { barConverter.convert(it) }
                .doFinally { _progressBarLiveData.postValue(false) }
                .subscribe(
                    { _salesDataBarSetLiveData.postValue(it) },
                    { _errorLiveData.postValue(Unit) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}