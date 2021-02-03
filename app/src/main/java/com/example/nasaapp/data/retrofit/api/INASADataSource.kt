package com.example.nasaapp.data.retrofit.api

import com.example.nasaapp.data.retrofit.entity.APODResponse
import com.example.nasaapp.data.retrofit.entity.FullInfoProject
import com.example.nasaapp.data.retrofit.entity.MainProjects
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface INASADataSource {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<APODResponse>

    @GET("planetary/apod")
    fun getPictureByDate(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<APODResponse>

    @GET("techport/api/projects")
    fun getProjects(@Query("api_key") apiKey: String) : Call<MainProjects>

    @GET("techport/api/projects/{id}?")
    fun getProject(@Path("id")id:String, @Query("api_key") apiKey: String): Call<MainProjects>
}