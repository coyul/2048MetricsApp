package com.coyul.a2048metricsapp.di.module.ui

import androidx.lifecycle.ViewModel
import com.coyul.a2048metricsapp.presentation.viewmodel.CountriesViewModel
import com.coyul.a2048metricsapp.presentation.viewmodel.MainMetricsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainMetricsViewModel::class)
    internal abstract fun bindMainMetricsViewModel(viewModel: MainMetricsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    internal abstract fun bindCountriesViewModel(viewModel: CountriesViewModel): ViewModel
}