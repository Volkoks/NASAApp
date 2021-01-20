package com.example.nasaapp.data.retrofit.api

import com.example.nasaapp.data.retrofit.entity.NASAResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface INASADataSource {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<NASAResponse>

    @GET("planetary/apod")
    fun getPictureByDate(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<NASAResponse>
}