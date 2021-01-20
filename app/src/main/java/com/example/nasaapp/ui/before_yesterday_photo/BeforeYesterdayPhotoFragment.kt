package com.example.nasaapp.ui.before_yesterday_photo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasaapp.R

class BeforeYesterdayPhotoFragment : Fragment() {

    companion object {
        fun newInstance() = BeforeYesterdayPhotoFragment()
    }

    private lateinit var viewModel: BeforeYesterdayPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.before_yesterday_photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeforeYesterdayPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}