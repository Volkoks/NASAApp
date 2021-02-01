package com.example.nasaapp.ui.test_recycler_view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapp.R
import com.example.nasaapp.common.Constant.FLAG_APOD
import com.example.nasaapp.common.Constant.FLAG_TECHPORT
import com.example.nasaapp.ui.test_recycler_view.recycler.TestRVAdapter
import com.example.nasaapp.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.test_r_v_fragment.*
import javax.inject.Inject

class TestRVFragment : DaggerFragment(R.layout.test_r_v_fragment) {

    companion object {
        fun newInstance() = TestRVFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TestRVViewModel::class.java)
    }
    private lateinit var adapter: TestRVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initHeading()
        viewModel.dataForRecyclerView.observe(viewLifecycleOwner, {
            adapter = TestRVAdapter(it)
            rv_apod_and_techport.adapter = adapter
            rv_apod_and_techport.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        })

        viewModel.dataForItem.observe(viewLifecycleOwner, {
            adapter.addItem(it)
        })

        mb_add_techport_rv.setOnClickListener {
            viewModel.getData(FLAG_TECHPORT)
        }
        mb_add_apod_rv.setOnClickListener {
            viewModel.getData(FLAG_APOD)
        }

    }

}