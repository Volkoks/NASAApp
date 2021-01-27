package com.example.nasaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.nasaapp.di.anatation.ViewModelKey
import com.example.nasaapp.ui.before_yesterday_photo.BeforeYesterdayPhotoViewModel
import com.example.nasaapp.ui.photo_of_the_past.PhotoOfThePastViewModel
import com.example.nasaapp.ui.yesterday_photo.YesterdayPhotoViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(PhotoOfThePastViewModel::class)
    abstract fun bindEarthViewModel(viewModel: PhotoOfThePastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(YesterdayPhotoViewModel::class)
    abstract fun bindYesterdayPhotoViewModel(viewModel: YesterdayPhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BeforeYesterdayPhotoViewModel::class)
    abstract fun bindBeforeYesterdayPhotoViewModel(viewModel: BeforeYesterdayPhotoViewModel): ViewModel
}