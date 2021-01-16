package com.example.nasaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.BuildConfig
import com.example.nasaapp.data.MainFragmentState
import com.example.nasaapp.data.retrofit.api.INASADataSource
import com.example.nasaapp.data.retrofit.entity.NASAResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    private val retrofit: INASADataSource
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }
    private val liveDataForViewToObserve: MutableLiveData<MainFragmentState> = MutableLiveData()


    fun getData(): LiveData<MainFragmentState> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MainFragmentState.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        launch {
            if (apiKey.isBlank()) {
                MainFragmentState.Error(Throwable("Нет ключа"))
            } else {
                retrofit.getPictureOfTheDay(apiKey).enqueue(object : Callback<NASAResponse> {
                    override fun onResponse(
                        call: Call<NASAResponse>,
                        response: Response<NASAResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataForViewToObserve.value =
                                MainFragmentState.Success(response.body()!!)
                        } else {
                            val messageError = response.message()
                            if (messageError.isNullOrEmpty()) {
                                liveDataForViewToObserve.value =
                                    MainFragmentState.Error(Throwable("Неопознаная ошибка"))
                            } else {
                                liveDataForViewToObserve.value =
                                    MainFragmentState.Error(Throwable(messageError))
                            }
                        }
                    }

                    override fun onFailure(call: Call<NASAResponse>, t: Throwable) {
                        liveDataForViewToObserve.value = MainFragmentState.Error(t)
                    }

                })
            }
        }
    }

}