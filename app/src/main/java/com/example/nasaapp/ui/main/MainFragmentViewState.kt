package com.example.nasaapp.ui.main

import com.example.nasaapp.data.retrofit.entity.NASAResponse

sealed class MainFragmentViewState {
    data class Success(val data : NASAResponse) : MainFragmentViewState()
    data class Error(val error: Throwable) : MainFragmentViewState()
    data class Loading(val progress: Int?) : MainFragmentViewState()
}