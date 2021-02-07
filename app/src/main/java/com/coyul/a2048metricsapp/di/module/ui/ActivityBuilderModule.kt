package com.coyul.a2048metricsapp.di.module.ui

import com.coyul.a2048metricsapp.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class,
            ViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}

