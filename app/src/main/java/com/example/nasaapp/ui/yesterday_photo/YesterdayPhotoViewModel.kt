package com.example.nasaapp.ui.yesterday_photo

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class YesterdayPhotoViewModel @Inject constructor(
    private val retrofit: INASADataSource
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }

    private val liveDataForViewToObserve: MutableLiveData<YesterdayViewState> = MutableLiveData()

    fun getData(): LiveData<YesterdayViewState> {
        sendServerRequest()
        return liveDataForViewToObserve
    }


    @SuppressLint("SimpleDateFormat")
    private fun getYesterday(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return dateFormat.format(calendar.time)
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = YesterdayViewState.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        launch {
            if (apiKey.isBlank()) {
                YesterdayViewState.Error(Throwable("Нет ключа"))
            } else {
                retrofit.getPictureByDate(apiKey, getYesterday())
                    .enqueue(object : Callback<APODResponse> {
                        override fun onResponse(
                            call: Call<APODResponse>,
                            response: Response<APODResponse>
                        ) {
                            liveDataForViewToObserve.value = response.body()?.let {
                                YesterdayViewState.Success(it)
                            }
                        }

                        override fun onFailure(call: Call<APODResponse>, t: Throwable) {
                            liveDataForViewToObserve.value = YesterdayViewState.Error(t)
                        }
                    })
            }
        }
    }
}