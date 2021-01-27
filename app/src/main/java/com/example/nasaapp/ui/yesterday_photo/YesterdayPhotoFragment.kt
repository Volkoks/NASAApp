package com.example.nasaapp.ui.yesterday_photo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.api.load
import com.example.nasaapp.R
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.yesterday_photo_fragment.*
import javax.inject.Inject

class YesterdayPhotoFragment : DaggerFragment(R.layout.yesterday_photo_fragment) {

    companion object {
        fun newInstance() = YesterdayPhotoFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: YesterdayPhotoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(YesterdayPhotoViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })

    }

    private fun renderData(data: YesterdayViewState) {
        when (data) {
            is YesterdayViewState.Loading -> {
            }

            is YesterdayViewState.Success -> {
                iv_yesterday_photo.load(data.data.url)
                tv_yesterday_description.text = data.data.explanation
                tv_yesterday_title.text = data.data.title
            }

            is YesterdayViewState.Error -> {
                Toast.makeText(context, data.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}