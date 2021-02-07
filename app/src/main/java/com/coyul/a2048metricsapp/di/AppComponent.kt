package com.coyul.a2048metricsapp.di

import android.app.Application
import com.coyul.a2048metricsapp.App
import com.coyul.a2048metricsapp.di.module.base.AppModule
import com.coyul.a2048metricsapp.di.module.base.NetworkModule
import com.coyul.a2048metricsapp.di.module.feature.CountriesModule
import com.coyul.a2048metricsapp.di.module.feature.ProductSalesModule
import com.coyul.a2048metricsapp.di.module.ui.ActivityBuildersModule
import com.coyul.a2048metricsapp.di.module.ui.ViewModelFactoryModule
import com.coyul.a2048metricsapp.di.scope.ApplicationScope

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        NetworkModule::class,
        ProductSalesModule::class,
        CountriesModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}