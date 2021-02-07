package com.coyul.a2048metricsapp.di.module.ui

import com.coyul.a2048metricsapp.presentation.fragment.CountriesFragment
import com.coyul.a2048metricsapp.presentation.fragment.MainMetricsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainMetricsFragment(): MainMetricsFragment

    @ContributesAndroidInjector
    abstract fun contributeCountriesFragment(): CountriesFragment
}