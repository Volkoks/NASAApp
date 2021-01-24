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
import com.example.nasaapp.common.Constant.DARK_THEME
import com.example.nasaapp.common.Constant.LIGHT_THEME
import com.example.nasaapp.common.Constant.MY_SETTING
import com.example.nasaapp.common.Constant.RED_THEME
import com.example.nasaapp.common.Constant.THEME_KEY
import com.example.nasaapp.ui.activity.MainActivity
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.setting_fragment.*
import javax.inject.Inject

class SettingFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting_motion.transitionToEnd()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)

        cg_change_theme.setOnCheckedChangeListener { group, checkedId ->
            val activity = activity as MainActivity
            when (checkedId) {
                R.id.chip_light_theme -> viewModel.themeState = LIGHT_THEME
                R.id.chip_dark_theme -> viewModel.themeState = DARK_THEME
                R.id.chip_red_theme->viewModel.themeState = RED_THEME
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

