package com.example.nasaapp.data

import com.example.nasaapp.data.retrofit.entity.NASAResponse

sealed class MainFragmentState {
    data class Success(val data : NASAResponse) : MainFragmentState()
    data class Error(val error: Throwable) : MainFragmentState()
    data class Loading(val progress: Int?) : MainFragmentState()
}