package com.example.nasaapp.ui.before_yesterday_photo

import com.example.nasaapp.data.retrofit.entity.NASAResponse

sealed class BeforeYesterdayViewState{
    data class Success(val data : NASAResponse) : BeforeYesterdayViewState()
    data class Error(val error: Throwable) : BeforeYesterdayViewState()
    data class Loading(val progress: Int?) : BeforeYesterdayViewState()
}
