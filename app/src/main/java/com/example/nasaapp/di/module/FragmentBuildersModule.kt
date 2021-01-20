package com.example.nasaapp.di.module

import com.example.nasaapp.ui.main.MainFragment
import com.example.nasaapp.ui.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment
}