package com.example.nasaapp.di.module

import com.example.nasaapp.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun builderActivity(): MainActivity
}