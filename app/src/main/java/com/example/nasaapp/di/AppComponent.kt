package com.example.nasaapp.di

import android.content.Context
import com.example.nasaapp.com.example.nasaapp.App
import com.example.nasaapp.di.module.ActivityBuildersModule
import com.example.nasaapp.di.module.ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApiModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}
