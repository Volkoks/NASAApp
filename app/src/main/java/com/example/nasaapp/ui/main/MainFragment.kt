package com.example.nasaapp.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.nasaapp.R
import com.example.nasaapp.ui.activity.MainActivity
import com.example.nasaapp.ui.photo_of_the_past.PhotoOfThePastFragment
import com.example.nasaapp.ui.setting.SettingFragment
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.main_fragment) {

    private var statePressedFAB = false

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickFAB()
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottomAppBar))
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
    }

    override fun onResume() {
        super.onResume()
        statePressedFAB = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_main_menu -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SettingFragment.newInstance())
                    ?.addToBackStack("Setting")?.commit()
            }
            R.id.earth_btn_menu -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, PhotoOfThePastFragment.newInstance())
                    ?.addToBackStack("Earth")?.commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun renderData(data: MainFragmentViewState) {
        when (data) {
            is MainFragmentViewState.Success -> {
                val dataNASA = data.data
                val url = dataNASA.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(activity, "ссылка пустая", Toast.LENGTH_SHORT).show()
                } else {
                    if (dataNASA.mediaType == "video") {
                        web_view.visibility = View.VISIBLE
                        image_picture_of_the_day.visibility = View.GONE
                        web_view.clearCache(true)
                        web_view.clearHistory()
                        web_view.settings.javaScriptEnabled = true
                        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
                        web_view.loadUrl(url)
                    } else {
                        image_picture_of_the_day.load(url) {
                            lifecycle(this@MainFragment)
                            error(R.drawable.ic_baseline_warning_24)
                            placeholder(R.drawable.ic_baseline_grass_24)
                        }
                    }
                }
                tv_title.text = dataNASA.title
                tv_description.text = dataNASA.explanation
            }
            is MainFragmentViewState.Error -> {
                Toast.makeText(context, "ERROR: ${data.error}", Toast.LENGTH_SHORT).show()
            }
            is MainFragmentViewState.Loading -> {

            }

        }
    }


    private fun setClickFAB(){
        floatingActionButton.setOnClickListener {
            if (statePressedFAB){
               deactivePressedFAB()
            }else{
                activePressedFAB()
            }
        }

    }

    private fun activePressedFAB(){
        statePressedFAB = true
        ObjectAnimator.ofFloat(setting_btn_container,"translationY",-250f).start()
        ObjectAnimator.ofFloat(setting_btn_container,"translationX",150f).start()

        setting_btn_container.animate()
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    setting_menu_btn.setOnClickListener {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.container, SettingFragment.newInstance())
                            ?.addToBackStack("Setting")?.commit()
                    }
                }

            })
    }
    private fun deactivePressedFAB(){
        statePressedFAB = false
        ObjectAnimator.ofFloat(setting_btn_container,"translationY",0f).start()
        ObjectAnimator.ofFloat(setting_btn_container,"translationX",0f).start()

        setting_btn_container.animate()
            .alpha(0f)
    }

}