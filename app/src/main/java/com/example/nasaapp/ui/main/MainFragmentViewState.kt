package com.example.nasaapp.ui.main

import com.example.nasaapp.data.retrofit.entity.APODResponse

sealed class MainFragmentViewState {
    data class Success(val data : APODResponse) : MainFragmentViewState()
    data class Error(val error: Throwable) : MainFragmentViewState()
    data class Loading(val progress: Int?) : MainFragmentViewState()
}