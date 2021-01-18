package com.example.nasaapp.ui.setting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapp.R
import com.example.nasaapp.ui.activity.MainActivity
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.setting_fragment.*
import javax.inject.Inject

class SettingFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SettingFragment()
        private const val MY_SETTING = "mySetting"
        private const val THEME_KEY = "Theme"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        cg_change_theme.setOnCheckedChangeListener { group, checkedId ->
            val activity = activity as MainActivity
            when (checkedId) {
                R.id.chip_light_theme -> viewModel.themeState = R.style.LightTheme
                R.id.chip_dark_theme -> viewModel.themeState = R.style.DarkTheme
                R.id.chip_red_theme->viewModel.themeState = R.style.RedTheme
            }
        }

        fab_save_setting.setOnClickListener {
            context?.getSharedPreferences(MY_SETTING,Context.MODE_PRIVATE)!!.edit {
                viewModel?.themeState?.let { it1 -> this.putInt(THEME_KEY, it1) }
            }
            activity?.recreate()
            }
        }
}

