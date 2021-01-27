package com.example.nasaapp.ui.before_yesterday_photo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.nasaapp.R
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.before_yesterday_photo_fragment.*
import javax.inject.Inject

class BeforeYesterdayPhotoFragment : DaggerFragment(R.layout.before_yesterday_photo_fragment) {

    companion object {
        fun newInstance() = BeforeYesterdayPhotoFragment()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory).get(BeforeYesterdayPhotoViewModel::class.java)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner,{renderData(it)})
    }
    private fun renderData(data: BeforeYesterdayViewState) {
        when (data) {
            is BeforeYesterdayViewState.Loading -> {
            }

            is BeforeYesterdayViewState.Success -> {
                iv_before_yesterday_photo.load(data.data.url)
                tv_before_yesterday_description.text = data.data.explanation
                tv_before_yesterday_title.text = data.data.title
            }

            is BeforeYesterdayViewState.Error -> {
                Toast.makeText(context, data.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}