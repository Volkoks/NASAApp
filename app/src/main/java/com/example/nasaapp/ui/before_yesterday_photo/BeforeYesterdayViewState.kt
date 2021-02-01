package com.example.nasaapp.ui.before_yesterday_photo

import com.example.nasaapp.data.retrofit.entity.APODResponse

sealed class BeforeYesterdayViewState{
    data class Success(val data : APODResponse) : BeforeYesterdayViewState()
    data class Error(val error: Throwable) : BeforeYesterdayViewState()
    data class Loading(val progress: Int?) : BeforeYesterdayViewState()
}
