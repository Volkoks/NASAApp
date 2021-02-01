package com.example.nasaapp.ui.test_recycler_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.BuildConfig
import com.example.nasaapp.common.Constant.FLAG_APOD
import com.example.nasaapp.common.Constant.FLAG_HEADING
import com.example.nasaapp.common.Constant.FLAG_TECHPORT
import com.example.nasaapp.data.recycler.DataForRecyclerView
import com.example.nasaapp.data.retrofit.api.INASADataSource
import com.example.nasaapp.data.retrofit.entity.APODResponse
import com.example.nasaapp.data.retrofit.entity.FullInfoProject
import com.example.nasaapp.data.retrofit.entity.MainProjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TestRVViewModel @Inject constructor(
    private val retrofit: INASADataSource
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }

    val dataForRecyclerView: MutableLiveData<MutableList<DataForRecyclerView>> =
        MutableLiveData()

    val dataForItem: MutableLiveData<DataForRecyclerView> = MutableLiveData()

    private val api: String = BuildConfig.NASA_API_KEY

    private var data = arrayListOf(
        DataForRecyclerView(FLAG_HEADING, "Heading")
    )

    private lateinit var localData: DataForRecyclerView

    fun initHeading() {
        dataForRecyclerView.value = data
    }

    fun getData(viewType: Int) {
        when (viewType) {
            FLAG_APOD -> {
                getDataForAPOD()
            }
            FLAG_TECHPORT -> {
//                getDataForTechport()
                getProject("12095")

            }
        }
    }

    private fun getDataForAPOD() {
        launch {
            retrofit.getPictureOfTheDay(api).enqueue(object : Callback<APODResponse> {
                override fun onResponse(
                    call: Call<APODResponse>,
                    response: Response<APODResponse>
                ) {
                    val title = response.body()?.title
                    title?.let { DataForRecyclerView(FLAG_APOD, it) }?.let { data.add(it) }
                    dataForItem.value = title?.let { DataForRecyclerView(FLAG_APOD, it) }


                }

                override fun onFailure(call: Call<APODResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun getDataForTechport() {
        launch {
            retrofit.getProjects(api).enqueue(object : Callback<MainProjects> {
                override fun onResponse(
                    call: Call<MainProjects>,
                    response: Response<MainProjects>
                ) {
                    response.body()?.projects?.id?.let { getProject(it) }
                }

                override fun onFailure(call: Call<MainProjects>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun getProject(id: String) {
        launch {
            retrofit.getProject(id, api).enqueue(object : Callback<FullInfoProject> {
                override fun onResponse(
                    call: Call<FullInfoProject>,
                    response: Response<FullInfoProject>
                ) {
                    val data = response.body()

                }

                override fun onFailure(call: Call<FullInfoProject>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }

}