package com.example.nasaapp.ui.photo_of_the_past

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.example.nasaapp.R
import com.example.nasaapp.common.Constant.BEFORE_YESTERDAY
import com.example.nasaapp.common.Constant.YESTERDAY
import com.example.nasaapp.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.photo_of_the_past_fragment.*
import javax.inject.Inject

class PhotoOfThePastFragment : DaggerFragment(R.layout.photo_of_the_past_fragment) {

    companion object {
        fun newInstance() = PhotoOfThePastFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PhotoOfThePastViewModel::class.java)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view_pager_earth_fragment.adapter = PhotoOfThePastVPAdapter(this)
        initTabLayoutMediator()
    }

    private fun initTabLayoutMediator() {
        TabLayoutMediator(tab_layout_earth_fragment, view_pager_earth_fragment) { tab, position ->
            tab.text = when (position) {
                0 -> YESTERDAY
                1 -> BEFORE_YESTERDAY
                else -> ""
            }
        }.attach()
    }

}