package com.example.nasaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.BuildConfig
import com.example.nasaapp.data.retrofit.api.INASADataSource
import com.example.nasaapp.data.retrofit.entity.APODResponse
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
    private val liveDataForViewToObserve: MutableLiveData<MainFragmentViewState> = MutableLiveData()


    fun getData(): LiveData<MainFragmentViewState> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MainFragmentViewState.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        launch {
            if (apiKey.isBlank()) {
                MainFragmentViewState.Error(Throwable("Нет ключа"))
            } else {
                retrofit.getPictureOfTheDay(apiKey).enqueue(object : Callback<APODResponse> {
                    override fun onResponse(
                        call: Call<APODResponse>,
                        response: Response<APODResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataForViewToObserve.value =
                                MainFragmentViewState.Success(response.body()!!)
                        } else {
                            val messageError = response.message()
                            if (messageError.isNullOrEmpty()) {
                                liveDataForViewToObserve.value =
                                    MainFragmentViewState.Error(Throwable("Неопознаная ошибка"))
                            } else {
                                liveDataForViewToObserve.value =
                                    MainFragmentViewState.Error(Throwable(messageError))
                            }
                        }
                    }

                    override fun onFailure(call: Call<APODResponse>, t: Throwable) {
                        liveDataForViewToObserve.value = MainFragmentViewState.Error(t)
                    }

                })
            }
        }
    }

}