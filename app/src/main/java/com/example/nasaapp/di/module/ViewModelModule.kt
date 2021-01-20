package com.example.nasaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.nasaapp.di.anatation.ViewModelKey
import com.example.nasaapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModele(viewModel: MainViewModel): ViewModel
}