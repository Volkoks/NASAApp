package com.example.nasaapp.di.module

import com.example.nasaapp.ui.before_yesterday_photo.BeforeYesterdayPhotoFragment
import com.example.nasaapp.ui.photo_of_the_past.PhotoOfThePastFragment
import com.example.nasaapp.ui.main.MainFragment
import com.example.nasaapp.ui.setting.SettingFragment
import com.example.nasaapp.ui.yesterday_photo.YesterdayPhotoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment

    @ContributesAndroidInjector
    abstract fun contributeEarthFragment(): PhotoOfThePastFragment

    @ContributesAndroidInjector
    abstract fun contributeYesterdayPhotoFragment(): YesterdayPhotoFragment

    @ContributesAndroidInjector
    abstract fun contributeBeforeYesterdayPhotoFragment(): BeforeYesterdayPhotoFragment

}