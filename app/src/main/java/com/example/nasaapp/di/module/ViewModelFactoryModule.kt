package com.example.nasaapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}