package com.example.nasaapp.ui.activity

import android.content.Context
import android.os.Bundle
import com.example.nasaapp.R
import com.example.nasaapp.common.Constant.DARK_THEME
import com.example.nasaapp.common.Constant.LIGHT_THEME
import com.example.nasaapp.common.Constant.RED_THEME
import com.example.nasaapp.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        private const val MY_SETTING = "mySetting"
        private const val THEME_KEY = "Theme"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(
            when (loadCurrentTheme()) {
                LIGHT_THEME -> LIGHT_THEME
                DARK_THEME -> DARK_THEME
                RED_THEME -> RED_THEME
                else -> R.style.DarkTheme
            }
        )
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun loadCurrentTheme(): Int =
        getSharedPreferences(MY_SETTING, Context.MODE_PRIVATE).getInt(
            THEME_KEY,
            LIGHT_THEME
        )
}