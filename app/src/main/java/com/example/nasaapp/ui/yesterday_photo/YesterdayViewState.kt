package com.example.nasaapp.ui.yesterday_photo

import com.example.nasaapp.data.retrofit.entity.NASAResponse

sealed class YesterdayViewState {
    data class Success(val data : NASAResponse) : YesterdayViewState()
    data class Error(val error: Throwable) : YesterdayViewState()
    data class Loading(val progress: Int?) : YesterdayViewState()
}