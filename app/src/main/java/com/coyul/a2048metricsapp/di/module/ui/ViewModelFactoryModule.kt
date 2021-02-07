package com.coyul.a2048metricsapp.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.coyul.a2048metricsapp.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}