package com.example.nasaapp.ui.activity

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.nasaapp.R
import com.example.nasaapp.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        private const val MY_SETTING = "mySetting"
        private const val THEME_KEY = "Theme"
        const val LIGHT_THEME = R.style.LightTheme
        const val DARK_THEME = R.style.DarkTheme
        const val RED_THEME = R.style.RedTheme
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(when(loadCurrentTheme()){
            LIGHT_THEME->R.style.LightTheme
            DARK_THEME->R.style.DarkTheme
            RED_THEME->R.style.RedTheme
            else->R.style.DarkTheme
        })
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
    private fun loadCurrentTheme(): Int =
        getSharedPreferences(MY_SETTING,Context.MODE_PRIVATE).getInt(THEME_KEY,
            LIGHT_THEME)
}