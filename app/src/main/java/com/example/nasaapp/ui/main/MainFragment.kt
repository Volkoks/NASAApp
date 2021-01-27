package com.example.nasaapp.ui.main

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

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    /**
     * Закоментированый код чтобы проверить рабатоспособность надувания layout через
     * конструктор DaggerFragment еслт кроме надувания в onCreateView нечего не происходит
     */
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.main_fragment, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottomAppBar))
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_main_menu -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SettingFragment.newInstance())?.addToBackStack("Setting")?.commit()
            }
            R.id.earth_btn_menu->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, PhotoOfThePastFragment.newInstance())?.addToBackStack("Earth")?.commit()
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

}