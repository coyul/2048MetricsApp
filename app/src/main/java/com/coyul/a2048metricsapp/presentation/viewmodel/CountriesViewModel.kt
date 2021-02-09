package com.coyul.a2048metricsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coyul.a2048metricsapp.domain.interactor.CountriesInteractor
import com.coyul.a2048metricsapp.domain.model.CountrySalesData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(private val countriesInteractor: CountriesInteractor) :
    ViewModel() {

    private val _listLiveData = MutableLiveData<List<CountrySalesData>>()
    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _errorLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val disposable = CompositeDisposable()
    val listLiveData: LiveData<List<CountrySalesData>> = _listLiveData
    val progressLiveData: LiveData<Boolean> = _progressLiveData
    val errorLiveData: LiveData<Unit> = _errorLiveData

    fun load() {
        _progressLiveData.postValue(true)
        disposable.add(
            countriesInteractor.getCountrySalesData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally { _progressLiveData.postValue(false) }
                .subscribe(
                    { _listLiveData.postValue(it) },
                    { _errorLiveData.postValue(Unit) }
                ))
    }
}