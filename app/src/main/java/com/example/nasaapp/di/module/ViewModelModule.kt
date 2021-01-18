package com.example.nasaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.nasaapp.di.anatation.ViewModelKey
import com.example.nasaapp.ui.main.MainViewModel
import com.example.nasaapp.ui.setting.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(viewModel: SettingViewModel): ViewModel
}